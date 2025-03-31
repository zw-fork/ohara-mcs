package org.ohara.mcs.web;

import com.google.protobuf.Any;
import org.ohara.mcs.OHaraMcsService;
import org.ohara.mcs.api.event.EventType;
import org.ohara.mcs.api.grpc.auto.Metadata;
import org.ohara.mcs.api.grpc.auto.MetadataDeleteRequest;
import org.ohara.mcs.api.grpc.auto.MetadataReadRequest;
import org.ohara.mcs.api.grpc.auto.Response;
import org.ohara.mcs.dto.User;
import org.ohara.msc.common.utils.GsonUtils;
import org.ohara.msc.dto.ServerAddress;
import org.ohara.msc.request.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/config")
public class UserController {

    @Resource
    private OHaraMcsService oHaraMcsService;

    @GetMapping("/get")
    public String get() {
        Payload payload = Payload.builder().build();
        payload.setNamespace("default");
        payload.setGroup("default_group");
        payload.setTag("default_tag");
        payload.setDataId("default#default_group#default_tag#org.ohara.mcs.dto.User");
        Response response = oHaraMcsService.request(payload, EventType.GET);
        Any data = response.getData();
        try {
            Metadata metadata = data.unpack(Metadata.class);
            return GsonUtils.getInstance().toJson(metadata);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/put")
    public String put(@RequestParam("name") String name, @RequestParam("age") String age) {
        Payload payload = Payload.builder().build();
        payload.setConfigData(new User(name, Integer.parseInt(age)));
        payload.setNamespace("default");
        payload.setGroup("default_group");
        payload.setTag("default_tag");
        // data_id 数据唯一表示
        payload.setDataId("default#default_group#default_tag#org.ohara.mcs.dto.User");
        Response response = oHaraMcsService.request(payload, EventType.PUT);
        Any data = response.getData();
        try {
            Metadata metadata = data.unpack(Metadata.class);
            return GsonUtils.getInstance().toJson(metadata);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/delete")
    public String delete() {
        Payload payload = Payload.builder().build();
        payload.setNamespace("default");
        payload.setGroup("default_group");
        payload.setTag("default_tag");
        payload.setDataId("default#default_group#default_tag#org.ohara.mcs.dto.User");
        Response response = oHaraMcsService.request(payload, EventType.DELETE);
        Any data = response.getData();
        try {
            Metadata metadata = data.unpack(Metadata.class);
            return GsonUtils.getInstance().toJson(metadata);
        } catch (Exception e) {
            return null;
        }
    }

}
