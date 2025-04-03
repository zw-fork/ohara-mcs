package org.ohara.mcs.config;

import org.ohara.mcs.OHaraMcsService;
import org.ohara.msc.client.OHaraMcsClient;
import org.ohara.msc.common.config.OHaraMcsConfig;
import org.ohara.msc.option.GrpcOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class OHaraMcsConfiguration {

    @Bean
    @Scope("singleton") // 显式声明单例
    public OHaraMcsService oHaraMcsClient(OHaraMcsConfig config) {
        GrpcOption option = new GrpcOption();
        // 初始化服务端集群地址
        option.initServers(config.getClusterAddress());
        // 构建OHaraMcsClient
        OHaraMcsClient mcsClient = OHaraMcsClient.builder(config.getNamespace(), option).build();
        return new OHaraMcsService(mcsClient);
    }

    public static void main(String[] args) {

    }
}
