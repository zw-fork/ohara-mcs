package org.ohara.mcs.listener;

import org.ohara.mcs.api.event.EventType;
import org.ohara.mcs.dto.User;
import org.ohara.mcs.spi.Join;
import org.ohara.mcs.spi.SpiExtensionFactory;
import org.ohara.msc.common.log.Log;
import org.ohara.msc.listener.AbstractConfigListener;
import org.ohara.msc.listener.ConfigListener;

import java.util.List;

@Join
public class UserConfigListener extends AbstractConfigListener<User> {
    @Override
    public void receive(String dataStr, User data, EventType eventType) {
        // TODO: Implement the logic to handle the received data
        Log.print("客户端收到配置变更推送: eventType=%s, dataStr=%s", eventType, dataStr);
    }

}
