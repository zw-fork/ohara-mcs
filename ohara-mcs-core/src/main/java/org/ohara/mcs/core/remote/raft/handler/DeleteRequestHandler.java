package org.ohara.mcs.core.remote.raft.handler;

import com.google.protobuf.Message;
import org.ohara.mcs.api.grpc.auto.Metadata;
import org.ohara.mcs.api.grpc.auto.MetadataDeleteRequest;
import org.ohara.mcs.api.grpc.auto.Response;
import org.ohara.mcs.api.event.GlobalEventBus;
import org.ohara.mcs.api.event.MetadataChangeEvent;
import org.ohara.mcs.api.result.ResponseHelper;
import org.ohara.mcs.spi.Join;
import org.ohara.mcs.api.event.EventType;
import org.ohara.msc.common.enums.ResponseCode;
import org.ohara.msc.common.log.Log;

/**
 * @author SpringCat
 * @date 2025-03-24 20:54
 */
@Join
public class DeleteRequestHandler extends AbstractConfigRequestHandler<MetadataDeleteRequest> {

    @Override
    public Response handle(Message request) {

        Log.print("===DeleteRequestHandler【开始】===> request: %s", request);

        MetadataDeleteRequest delete = (MetadataDeleteRequest) request;

        String key = storage.key(delete.getNamespace(), delete.getGroup(), delete.getTag(), delete.getDataId());
        if (key == null) {
            return ResponseHelper.error(ResponseCode.PARAM_INVALID.getCode(), "key is null");
        }

        Metadata oldData = storage.get(key);
        if (oldData != null) {
            Metadata removed = storage.delete(key);
            if (removed != null) {
                GlobalEventBus.post(new MetadataChangeEvent(removed, EventType.DELETE));
            }
        }
        Log.print("===DeleteRequestHandler【完成】===> request: %s", request);

        return ResponseHelper.success();
    }

    @Override
    public Class<?> clazz() {
        return MetadataDeleteRequest.class;
    }
}
