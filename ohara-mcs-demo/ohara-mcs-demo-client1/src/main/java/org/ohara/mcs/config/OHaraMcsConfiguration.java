package org.ohara.mcs.config;

import org.ohara.mcs.OHaraMcsService;
import org.ohara.msc.client.OHaraMcsClient;
import org.ohara.msc.common.config.OHaraMcsConfig;
import org.ohara.msc.listener.ConfigListener;
import org.ohara.msc.option.GrpcOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import java.util.List;
import java.util.ServiceLoader;

@Configuration
public class OHaraMcsConfiguration {

    @Bean
    @Scope("singleton") // 显式声明单例
    public OHaraMcsService oHaraMcsClient(OHaraMcsConfig config) {
        GrpcOption option = new GrpcOption();
        option.initServers(config.getClusterAddress());
        OHaraMcsClient mcsClient = OHaraMcsClient.builder(config.getNamespace(), option).build();
        return new OHaraMcsService(mcsClient);
    }
}
