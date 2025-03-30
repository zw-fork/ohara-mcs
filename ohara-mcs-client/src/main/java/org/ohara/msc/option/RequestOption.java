package org.ohara.msc.option;

import com.google.protobuf.Timestamp;
import lombok.Data;
import org.ohara.mcs.api.grpc.auto.MetadataType;
import org.ohara.msc.common.config.OHaraMcsConfig;

import java.util.Map;

@Data
public abstract class RequestOption {

    public abstract String protocol();
}
