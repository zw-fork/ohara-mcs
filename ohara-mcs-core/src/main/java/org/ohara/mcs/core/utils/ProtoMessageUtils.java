package org.ohara.mcs.core.utils;

import com.google.protobuf.Message;
import org.ohara.mcs.api.grpc.auto.MetadataDeleteRequest;
import org.ohara.mcs.api.grpc.auto.MetadataReadRequest;
import org.ohara.mcs.api.grpc.auto.MetadataWriteRequest;
import org.ohara.mcs.core.remote.RpcServer;
import org.ohara.mcs.core.remote.grpc.GrpcService;
import org.ohara.mcs.core.serializer.Serializer;
import org.ohara.mcs.spi.SpiExtensionFactory;

import java.util.Arrays;
import java.util.List;

public class ProtoMessageUtils {

    public static Message parse(Serializer serializer, byte[] bytes) {
        List<Class<? extends Message>> messages = List.of(
                MetadataReadRequest.class,
                MetadataWriteRequest.class,
                MetadataDeleteRequest.class
        );

        for (Class<? extends Message> message : messages) {
            try {
                return serializer.deserialize(bytes, message);
            } catch (Exception ignored) {}
        }

        throw new IllegalArgumentException("Message解析失败，bytes: " + Arrays.toString(bytes));
    }

}
