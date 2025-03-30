package org.ohara.mcs.core.remote.raft.processor;

import com.alipay.sofa.jraft.rpc.RpcContext;
import org.ohara.mcs.api.grpc.auto.MetadataDeleteRequest;
import org.ohara.mcs.api.grpc.auto.MetadataReadRequest;
import org.ohara.mcs.api.grpc.auto.MetadataWriteRequest;
import org.ohara.mcs.core.remote.raft.RaftServer;
import org.ohara.mcs.core.serializer.Serializer;
import org.ohara.msc.common.enums.RaftGroup;

public class DeleteRequestRpcProcessor extends AbstractRpcProcessor<MetadataDeleteRequest> {

    public DeleteRequestRpcProcessor(RaftServer server, Serializer serializer) {
        super(server, serializer, true);
    }

    @Override
    public void handleRequest(RpcContext ctx, MetadataDeleteRequest request) {
        super.handleRequest(ctx, request);
    }

    @Override
    protected String extractRaftGroup(MetadataDeleteRequest request) {
        return request.getRaftGroup();
    }

    @Override
    public String interest() {
        return MetadataDeleteRequest.class.getName();
    }
}