package org.ohara.mcs.config;

import org.ohara.mcs.api.event.EventType;
import org.ohara.mcs.spi.Join;
import org.ohara.mcs.spi.SpiExtensionFactory;
import org.ohara.msc.common.log.Log;
import org.ohara.msc.dto.ServerAddress;
import org.ohara.msc.listener.AbstractConfigListener;
import org.ohara.msc.listener.ConfigListener;

import java.util.List;
import java.util.ServiceLoader;

@Join
public class TestServerAddressConfigListener extends AbstractConfigListener<ServerAddress> {
    @Override
    public void receive(String dataStr, ServerAddress data, EventType eventType) {
        // TODO: Implement the logic to handle the received ServerAddress configuration
        Log.print("ServerAddressConfigListener->客户端收到配置变更: eventType=%s, data=%s", eventType, dataStr);
    }
}