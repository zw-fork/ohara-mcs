package org.ohara.mcs.core.remote.raft.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ohara.mcs.api.grpc.auto.MetadataWriteRequest;
import org.ohara.mcs.api.grpc.auto.Response;

class RequestDispatcherTest {

    @Test
    void test_getInstance() {
        RequestDispatcher dispatcher = RequestDispatcher.getInstance();
        Response response = dispatcher.dispatch(MetadataWriteRequest.newBuilder().build(), MetadataWriteRequest.class);
    }

}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme