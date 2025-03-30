package org.ohara.mcs.core.remote;

import org.ohara.msc.common.config.OHaraMcsConfig;
import org.ohara.mcs.spi.SPI;

@SPI
public interface RpcServer {

    void init(OHaraMcsConfig config);

    int port();

    int portOffset();

    void start();

    void stop();

    void await();

}