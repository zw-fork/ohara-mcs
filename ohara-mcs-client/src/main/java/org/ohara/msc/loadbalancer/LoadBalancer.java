package org.ohara.msc.loadbalancer;

import org.ohara.mcs.spi.SPI;
import org.ohara.msc.dto.ServerAddress;

import java.util.List;

@SPI("random")
public interface LoadBalancer {
    ServerAddress select(List<ServerAddress> servers);
}