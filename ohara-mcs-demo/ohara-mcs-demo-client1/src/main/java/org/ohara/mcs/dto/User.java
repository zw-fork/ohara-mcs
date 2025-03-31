package org.ohara.mcs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ohara.msc.listener.ConfigData;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements ConfigData {
    private String name;
    private Integer age;
}
