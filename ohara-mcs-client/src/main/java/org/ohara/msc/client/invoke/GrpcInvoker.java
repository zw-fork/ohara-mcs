package org.ohara.msc.client.invoke;

import com.google.protobuf.Message;
import com.google.protobuf.Timestamp;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ohara.mcs.api.event.EventType;
import org.ohara.mcs.api.grpc.auto.*;
import org.ohara.mcs.spi.SpiExtensionFactory;
import org.ohara.msc.client.OHaraMcsClient;
import org.ohara.msc.common.enums.RaftGroup;
import org.ohara.msc.common.exception.InitializationException;
import org.ohara.msc.common.exception.OHaraMcsClientException;
import org.ohara.msc.context.OHaraMcsContext;
import org.ohara.msc.lifecycle.Closeable;
import org.ohara.msc.option.GrpcOption;
import org.ohara.msc.option.RequestOption;
import org.ohara.msc.remote.RpcClient;
import org.ohara.msc.remote.grpc.GrpcClient;
import org.ohara.msc.request.Payload;

import java.util.Map;

public class GrpcInvoker extends AbstractInvoker<Message, GrpcOption>  {

    private final GrpcClient grpcClient;

    public GrpcInvoker(OHaraMcsClient client) {
        super(client);
        GrpcOption grpcOption = (GrpcOption) client.getOption();
        if (grpcOption == null) {
            throw new InitializationException("Init Grpc Invoker fail, GrpcOption is empty.");
        }
        if (StringUtils.isBlank(client.getNamespace())) {
            throw new IllegalArgumentException("Init Grpc Invoker fail, Namespace is null.");
        }
        if (CollectionUtils.isEmpty(grpcOption.getServerAddresses())) {
            throw new IllegalArgumentException("Init Grpc Invoker fail, ServerAddresses is empty.");
        }
        grpcClient = (GrpcClient) SpiExtensionFactory.getExtension(protocol(), RpcClient.class);
        grpcClient.init(client.getNamespace(), grpcOption.getServerAddresses());
    }

    public Response innerInvoke(Message request, EventType eventType) {
        return switch (eventType) {
            case PUT -> grpcClient.put((MetadataWriteRequest) request);
            case DELETE -> grpcClient.delete((MetadataDeleteRequest) request);
            case GET -> grpcClient.get((MetadataReadRequest) request);
            default -> throw new IllegalArgumentException("Unsupported event type: " + eventType);
        };
    }

    @Override
    public Response invoke(OHaraMcsContext context, Payload request) {
        GrpcOption option = getOption();
        Message message = convert(context, option, request);
        return innerInvoke(message, request.getEventType());
    }

    @Override
    public String protocol() {
        return "grpc";
    }

    @Override
    public Message convert(OHaraMcsContext context, GrpcOption option, Payload request) {
        return switch (request.getEventType()) {
            case PUT -> MetadataWriteRequest.newBuilder()
                    .setRaftGroup(RaftGroup.CONFIG_CENTER_GROUP.getName())
                    .setNamespace(context.getNamespace())
                    .setGroup(request.getGroup())
                    .setTag(request.getTag())
                    .setDataId(request.getDataId())
                    .setMetadata(buildMetadata(context, option, request))
                    .build();
            case DELETE -> MetadataDeleteRequest.newBuilder()
                    .setRaftGroup(RaftGroup.CONFIG_CENTER_GROUP.getName())
                    .setNamespace(context.getNamespace())
                    .setGroup(request.getGroup())
                    .setTag(request.getTag())
                    .setDataId(request.getDataId())
                    .build();
            case GET -> MetadataReadRequest.newBuilder()
                    .setRaftGroup(RaftGroup.CONFIG_CENTER_GROUP.getName())
                    .setNamespace(context.getNamespace())
                    .setGroup(request.getGroup())
                    .setTag(request.getTag())
                    .setDataId(request.getDataId())
                    .build();
            default -> throw new IllegalArgumentException("Unsupported event type: " + request.getEventType());
        };

    }

    private Metadata buildMetadata(OHaraMcsContext context, GrpcOption option, Payload request) {
        return Metadata.newBuilder()
                .setNamespace(context.getNamespace())
                .setGroup(request.getGroup())
                .setTag(request.getTag())
                .setDataId(request.getDataId())
                .setDataKey(request.getConfigData().key())
                .setType(request.getType())
                .setContent(context.getConfigDataString())
                .setMd5(context.getMd5())
                .setGmtCreate(Timestamp.newBuilder().setSeconds(request.getGmtCreate()))
                .setGmtModified(Timestamp.newBuilder().setSeconds(request.getGmtModified()))
//                .putAllExt(request.getExt())
                .build();
    }

    @Override
    public void shutdown() throws OHaraMcsClientException {
        grpcClient.shutdown();
    }
}
