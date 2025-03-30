package org.ohara.msc.listener;

import org.ohara.mcs.api.event.EventType;

public class Demo1ConfigListener extends AbstractConfigListener<Demo1DTO> {

    @Override
    public void receive(String dataStr, Demo1DTO data, EventType eventType) {
        System.out.println("Demo1ConfigListener收到配置变更: " + dataStr);
    }
}