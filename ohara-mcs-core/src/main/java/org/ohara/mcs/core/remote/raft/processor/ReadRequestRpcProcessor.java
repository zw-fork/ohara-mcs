package org.ohara.mcs.core.remote.raft.processor;

import com.alipay.sofa.jraft.rpc.RpcContext;
import org.ohara.mcs.api.grpc.auto.MetadataReadRequest;
import org.ohara.mcs.api.grpc.auto.MetadataWriteRequest;
import org.ohara.mcs.core.remote.raft.RaftServer;
import org.ohara.mcs.core.serializer.Serializer;
import org.ohara.msc.common.enums.RaftGroup;

public class ReadRequestRpcProcessor extends AbstractRpcProcessor<MetadataReadRequest> {

    public ReadRequestRpcProcessor(RaftServer server, Serializer serializer) {
        super(server, serializer, false);
    }

    @Override
    public void handleRequest(RpcContext ctx, MetadataReadRequest request) {
        super.handleRequest(ctx, request);
    }

    @Override
    protected String extractRaftGroup(MetadataReadRequest request) {
        return request.getRaftGroup();
    }
    
    @Override
    public String interest() {
        return MetadataReadRequest.class.getName();
    }
}