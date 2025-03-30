package org.ohara.mcs.core.remote.raft.client;

import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.rpc.RpcClient;
import com.google.common.eventbus.Subscribe;
import org.ohara.mcs.api.event.GlobalEventBus;
import org.ohara.mcs.core.event.LeaderRefreshEvent;
import org.ohara.mcs.api.listener.Listener;
import org.ohara.mcs.core.remote.raft.helper.RaftHelper;
import org.ohara.msc.common.log.Log;

/**
 * @author SpringCat
 */
public class RaftClientFactory {

    // Raft RPC
    private RpcClient rpcClient;

    private PeerId leaderId;

    public boolean initialize = false;

    private static RaftClientFactory INSTANCE;

    {
        GlobalEventBus.register(new LeaderRefreshListener());
    }

    public class LeaderRefreshListener implements Listener<LeaderRefreshEvent> {

        @Override
        @Subscribe
        public void onSubscribe(LeaderRefreshEvent event) {
            Log.print("LeaderRefreshListener->收到监听消息, leaderId=%s", event.peerId());
            leaderId = event.peerId();
        }

    }

    private RaftClientFactory() {
    }

    public static RaftClientFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RaftClientFactory();
        }
        return INSTANCE;
    }

    // @Bean(initMethod="init")
    public void init() {
        this.rpcClient = RaftHelper.initClient();
        this.rpcClient.init(null);
        initialize = true;
    }

    public PeerId getLeaderId() {
        return leaderId;
    }

    public RpcClient getRpcClient() {
        return rpcClient;
    }

}
