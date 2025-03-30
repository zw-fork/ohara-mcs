package org.ohara.msc.remote;


import org.ohara.mcs.api.grpc.auto.MetadataDeleteRequest;
import org.ohara.mcs.api.grpc.auto.MetadataReadRequest;
import org.ohara.mcs.api.grpc.auto.MetadataWriteRequest;
import org.ohara.mcs.api.grpc.auto.Response;
import org.ohara.mcs.spi.SPI;
import org.ohara.msc.future.RequestFuture;
import org.ohara.msc.lifecycle.Closeable;

@SPI(value = "grpc")
public interface RpcClient<R,S> {

    S request(R request);

    RequestFuture<S> requestFuture(R request);


}
