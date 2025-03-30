package org.ohara.msc.listener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.ohara.mcs.api.event.EventType;
import org.ohara.mcs.api.grpc.auto.Metadata;
import org.ohara.mcs.api.grpc.auto.MetadataType;
import org.ohara.msc.common.utils.GsonUtils;
import org.ohara.msc.common.utils.MD5Utils;

import java.util.ServiceLoader;

public class ConfigListenerTest {

    @Test
    public void test() {
        ServiceLoader.load(ConfigListener.class).forEach(ConfigListener::register);

        Demo1DTO demo1DTO = new Demo1DTO("name");
        Metadata metadata = Metadata.newBuilder()
                .setDataId("dataId")
                .setType(MetadataType.JSON)
                .setTag("tag")
                .setDataKey(demo1DTO.key())
                .setContent(GsonUtils.getInstance().toJson(demo1DTO))
                .setMd5(MD5Utils.calculateMD5(GsonUtils.getInstance().toJson(demo1DTO)))
                .setGroup("group")
                .setNamespace("default")
                .build();
        ConfigListenerManager.fireEvent(metadata, EventType.PUT);

        Demo2DTO demo2DTO = new Demo2DTO("name");
        Metadata metadata2 = Metadata.newBuilder()
                .setDataId("dataId")
                .setType(MetadataType.JSON)
                .setTag("tag")
                .setDataKey(demo2DTO.key())
                .setContent(GsonUtils.getInstance().toJson(demo2DTO))
                .setMd5(MD5Utils.calculateMD5(GsonUtils.getInstance().toJson(demo2DTO)))
                .setGroup("group")
                .setNamespace("default")
                .build();
        ConfigListenerManager.fireEvent(metadata2, EventType.PUT);
    }
}

