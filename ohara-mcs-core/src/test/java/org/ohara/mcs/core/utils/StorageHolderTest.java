package org.ohara.mcs.core.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ohara.mcs.api.grpc.auto.Metadata;
import org.ohara.mcs.core.remote.RpcServer;
import org.ohara.mcs.core.remote.raft.handler.RequestHandler;
import org.ohara.mcs.core.storage.AbstractStorage;
import org.ohara.mcs.core.storage.MetadaStorage;
import org.ohara.mcs.core.storage.Storage;
import org.ohara.mcs.spi.SpiExtensionFactory;

import java.util.List;


public class StorageHolderTest {

    @Test
    public void getInstance_test() {
        MetadaStorage metadata = StorageHolder.getInstance("metadata");
        Assertions.assertNotNull(metadata);
    }

    @Test
    @SuppressWarnings("all")
    public void getInstance_test2() {
        List<RpcServer> extensions = SpiExtensionFactory.getExtensions(RpcServer.class);
        RpcServer rpcServer = SpiExtensionFactory.getDefaultExtension(RpcServer.class);
        List<RequestHandler> handlers = SpiExtensionFactory.getExtensions(RequestHandler.class);
        System.out.println(handlers);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme