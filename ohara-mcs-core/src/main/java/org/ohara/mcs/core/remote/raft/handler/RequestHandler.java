package org.ohara.mcs.core.remote.raft.handler;

import com.google.protobuf.Message;
import org.ohara.mcs.api.grpc.auto.Response;
import org.ohara.mcs.spi.SPI;

/**
 * 用于Raft接收请求后的处理逻辑，不是RPC服务哈～
 * @author SpringCat
 */
@SPI
public interface RequestHandler<T extends Message> {

    Response onApply(Message request);

    void onError(Throwable error);

    String group();

    Class<?> clazz();
}
