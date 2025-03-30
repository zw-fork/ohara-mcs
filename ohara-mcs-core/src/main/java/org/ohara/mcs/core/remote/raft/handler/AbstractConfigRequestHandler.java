package org.ohara.mcs.core.remote.raft.handler;

import com.google.protobuf.Message;
import org.ohara.mcs.api.grpc.auto.Metadata;
import org.ohara.mcs.api.grpc.auto.Response;
import org.ohara.mcs.api.result.ResponseHelper;
import org.ohara.mcs.core.storage.MetadaStorage;
import org.ohara.mcs.core.utils.StorageHolder;
import org.ohara.msc.common.enums.RaftGroup;
import org.ohara.msc.common.enums.ResponseCode;
import org.ohara.msc.common.exception.OHaraMcsException;

/**
 * @author SpringCat
 */
public abstract class AbstractConfigRequestHandler<T extends Message> implements RequestHandler<T> {

    protected final MetadaStorage storage = StorageHolder.getInstance("metadata");

    public abstract Class<?> clazz();

    public Metadata get(String key) {
        return storage.get(key);
    }

    public boolean put(Metadata metadata) {
        return storage.put(metadata) != null;
    }

    @Override
    public String group() {
        return RaftGroup.CONFIG_CENTER_GROUP.getName();
    }

    @Override
    public Response onApply(Message request) {
        try {
            return handle(request);
        } catch (Exception e) {
            return ResponseHelper.error(ResponseCode.SYSTEM_ERROR.getCode(), "Processing failed: " + e.getMessage());
        }
    }

    @Override
    public void onError(Throwable error) {
        throw new OHaraMcsException(error);
    }

    public abstract Response handle(Message request);
}
