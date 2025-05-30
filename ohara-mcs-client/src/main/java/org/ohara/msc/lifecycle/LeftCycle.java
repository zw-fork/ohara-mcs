package org.ohara.msc.lifecycle;

import org.ohara.msc.common.exception.OHaraMcsClientException;

import java.util.concurrent.TimeUnit;

public interface LeftCycle {

    void init() throws Exception;


    void destroy(Integer timeout, TimeUnit unit);

}