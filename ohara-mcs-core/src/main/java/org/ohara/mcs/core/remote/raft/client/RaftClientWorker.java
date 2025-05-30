package org.ohara.mcs.core.remote.raft.client;

import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.error.RemotingException;
import com.alipay.sofa.jraft.rpc.RpcClient;
import com.alipay.sofa.jraft.util.Endpoint;
import com.google.protobuf.Message;
import org.ohara.mcs.api.grpc.auto.MetadataSubscribeRequest;
import org.ohara.mcs.api.grpc.auto.Response;
import org.ohara.msc.common.exception.OHaraMcsException;
import org.ohara.msc.common.log.Log;

/**
 * @author SpringCat
 * @date 2025-03-26 09:35
 */
public class RaftClientWorker {

    private final RaftClientFactory factory;

    private final RpcClient rpcClient;

    public RaftClientWorker(RaftClientFactory factory) {
        if (!factory.initialize) {
            factory.init();
        }
        this.factory = factory;
        this.rpcClient = factory.getRpcClient();
    }

    // TODO 失败重试退避序列
    public Response invoke(Message request) throws RemotingException, InterruptedException {
        return invoke(request, 3000L);
    }

    public Response invoke(Message request, long timeout) throws RemotingException, InterruptedException {
        Log.print("===RaftClientWorker invoke to leader===>: %s", factory.getLeaderId());
        PeerId leaderId = factory.getLeaderId();
        Endpoint endpoint = new Endpoint(leaderId.getIp(), leaderId.getPort());
        return (Response) rpcClient.invokeSync(endpoint, request, timeout);
    }
}
