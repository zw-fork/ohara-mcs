package org.ohara.msc.option;


import com.google.protobuf.Timestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;
import org.ohara.mcs.api.event.Event;
import org.ohara.mcs.api.event.EventType;
import org.ohara.msc.dto.ServerAddress;
import org.ohara.msc.utils.ServerAddressConverter;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GrpcOption extends RequestOption {

    private List<ServerAddress> serverAddresses;

    //private EventType eventType;

    @Override
    public String protocol() {
        return "grpc";
    }

    public void initServers(List<String> addresses) {
        this.serverAddresses = ServerAddressConverter.convert(addresses);
        if (CollectionUtils.isEmpty(serverAddresses)) {
            throw new IllegalArgumentException("Invalid params: the server address is empty");
        }
    }

}
