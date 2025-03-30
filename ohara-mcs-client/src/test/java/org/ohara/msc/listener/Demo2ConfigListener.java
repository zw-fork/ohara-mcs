package org.ohara.msc.listener;

import org.ohara.mcs.api.event.EventType;

public class Demo2ConfigListener extends AbstractConfigListener<Demo2DTO> {

    @Override
    public void receive(String dataStr, Demo2DTO data, EventType eventType) {
        System.out.println("Demo2ConfigListener收到配置变更: " + dataStr);
    }
}