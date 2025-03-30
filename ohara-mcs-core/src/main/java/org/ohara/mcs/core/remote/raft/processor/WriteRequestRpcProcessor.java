package org.ohara.mcs.core.remote.raft.processor;

import com.alipay.sofa.jraft.Status;
import com.alipay.sofa.jraft.rpc.RpcContext;
import com.google.protobuf.Message;
import org.ohara.mcs.api.grpc.auto.MetadataReadRequest;
import org.ohara.mcs.api.grpc.auto.MetadataWriteRequest;
import org.ohara.mcs.core.remote.raft.RaftServer;
import org.ohara.mcs.core.serializer.Serializer;
import org.ohara.msc.common.enums.RaftGroup;


public class WriteRequestRpcProcessor extends AbstractRpcProcessor<MetadataWriteRequest> {

    public WriteRequestRpcProcessor(RaftServer server, Serializer serializer) {
        super(server, serializer, true);
    }

    @Override
    public void handleRequest(RpcContext ctx, MetadataWriteRequest request) {
        super.handleRequest(ctx, request);
    }

    @Override
    protected String extractRaftGroup(MetadataWriteRequest request) {
        return request.getRaftGroup();
    }

    @Override
    public String interest() {
        return MetadataWriteRequest.class.getName();
    }
}
