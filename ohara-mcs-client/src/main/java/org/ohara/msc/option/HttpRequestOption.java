package org.ohara.msc.option;

import java.util.Map;

// TODO
public class HttpRequestOption extends RequestOption {

    private String url;

    private Map<String, String> header;

    private String method;

    private Long createTimestamp;

    private Long updateTimestamp;

    @Override
    public String protocol() {
        return "http";
    }
}
