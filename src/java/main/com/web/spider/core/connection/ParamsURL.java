package com.web.spider.core.connection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by yurl on 2017/12/4.
 */
public class ParamsURL {

    private URL url;
    private Map<String, String> property;

    public ParamsURL(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
