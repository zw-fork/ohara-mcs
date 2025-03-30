package org.ohara.msc.listener;

import org.ohara.mcs.api.event.EventType;
import org.ohara.mcs.spi.SPI;

@SPI
public interface ConfigListener<T extends ConfigData> {

    void receive(String dataStr, T data, EventType eventType);

    void register();
}