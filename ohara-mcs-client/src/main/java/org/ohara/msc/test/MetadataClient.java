package org.ohara.msc.test;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.ohara.mcs.api.grpc.auto.*;
import org.ohara.mcs.api.event.EventType;
import org.ohara.msc.common.enums.RaftGroup;
import org.ohara.msc.common.log.Log;
import org.ohara.msc.common.utils.MD5Utils;

import java.util.concurrent.TimeUnit;

// 客户端
@Deprecated
public class MetadataClient {
    private final MetadataServiceGrpc.MetadataServiceBlockingStub blockingStub;
    private final MetadataServiceGrpc.MetadataServiceStub asyncStub;
    private ManagedChannel channel;

    public MetadataClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext()
            .build();
        this.blockingStub = MetadataServiceGrpc.newBlockingStub(channel);
        this.asyncStub = MetadataServiceGrpc.newStub(channel);
    }

    public void putConfig(String dataId, String content) {
        Metadata metadata = Metadata.newBuilder()
            .setDataId(dataId)
                .setType(MetadataType.JSON)
                .setTag("tag")
            .setContent(content)
                .setMd5(MD5Utils.calculateMD5(content))
                .setGroup("group")
            .setNamespace("default") // 根据实际需要设置
            .build();

        MetadataWriteRequest request = MetadataWriteRequest.newBuilder()
            .setMetadata(metadata)
                .setRaftGroup(RaftGroup.CONFIG_CENTER_GROUP.getName())
            .build();

        Response response = blockingStub.put(request);
        System.out.println("Put config result: " + response.getSuccess());
    }

    // 配置删除方法
    public void deleteConfig(String dataId) {
        MetadataDeleteRequest request = MetadataDeleteRequest.newBuilder()
            .setDataId(dataId)
                .setDataId(dataId)
                .setTag("tag")
                .setGroup("group")
                .setNamespace("default") // 根据实际需要设置
                .setRaftGroup(RaftGroup.CONFIG_CENTER_GROUP.getName())

            .build();

        Response response = blockingStub.delete(request);
        System.out.println("Delete config result: " + response.getSuccess());
    }

    // 启动配置监听
    public void startListening() {
        MetadataSubscribeRequest request = MetadataSubscribeRequest.newBuilder()
            .setNamespace("default") // 参数已无过滤作用，但需要符合proto定义
                .setRaftGroup(RaftGroup.CONFIG_CENTER_GROUP.getName())
            .build();

        asyncStub.subscribe(request, new StreamObserver<MetadataSubscribeResponse>() {
            @Override
            public void onNext(MetadataSubscribeResponse response) {
                Log.print("配置订阅监听: response=%s", response);
                handleConfigChange(response);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("配置订阅监听出错: " + t.getMessage());
                // 可添加重连逻辑
                reconnect();
            }

            @Override
            public void onCompleted() {
                System.out.println("服务端结束流");
            }
        });
    }

    private void handleConfigChange(MetadataSubscribeResponse response) {
        try {
            String operation = response.getOpType();
            Metadata metadata = response.getMetadata();
            
            System.out.printf("[%s] 监听配置变更: %s | 内容: %s%n",
                operation,
                metadata.getDataId(),
                metadata.getContent());
            
            // 根据操作类型处理业务逻辑
            switch (EventType.valueOf(operation)) {
                case PUT:
                    handleConfigUpdate(metadata);
                    break;
                case DELETE:
                    handleConfigDelete(metadata.getDataId());
                    break;
            }
        } catch (Exception e) {
            System.err.println("处理配置变更异常: " + e.getMessage());
        }
    }

    private void handleConfigUpdate(Metadata metadata) {
        // 实现具体的更新逻辑
        System.out.println("处理配置更新: " + metadata.getDataId());
    }

    private void handleConfigDelete(String dataId) {
        // 实现具体的删除处理
        System.out.println("处理配置删除: " + dataId);
    }

    private void reconnect() {
        // 实现重连逻辑
        System.out.println("尝试重新连接...");
        this.channel.shutdown();
        this.channel = ManagedChannelBuilder.forAddress("127.0.0.1", 8000)
            .usePlaintext()
            .build();
        startListening();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // 使用示例
    public static void main(String[] args) throws InterruptedException {
        MetadataClient client = new MetadataClient("127.0.0.1", 8000);
        
        // 启动监听
        client.startListening();
        
        // 测试操作
        client.putConfig("test-config", "config content23335555");
        Thread.sleep(1000);
        client.deleteConfig("test-config");
        
        // 保持连接
        Thread.sleep(60000);
        client.shutdown();
    }
}
