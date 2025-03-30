package org.ohara.msc.context;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ohara.mcs.api.event.EventType;

/**
 * @author SpringCat
 */
@Data
@NoArgsConstructor
public class OHaraMcsContext {

    private String sign;

    private String md5;

    private String namespace;

    private String configDataString;
}
