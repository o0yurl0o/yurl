package com.web.spider.core.connection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yurl on 2017/12/4.
 */
public class ParamsURL {

    private URL url;
    private Map<String, String> property;
    private int timeout = 5000;

    public ParamsURL(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setRequestProperty(String key, String value) {
        property.put(key, value);
    }

    public void setRequestProperty(Map<String, String> propertyMap) {
        propertyMap.entrySet().stream().forEach(e -> property.put(e.getKey(), e.getValue()));
    }

    public void setRequestProperty(Properties property) {
        property.entrySet().stream().forEach(e -> property.put(e.getKey(), e.getValue()));
    }

    public String getRequestProperty(String key) {
        return property.get(key);
    }

    public void setTimeout(int timeout) {
        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout must certificate");
        }
        this.timeout = timeout;
    }

    public URLConnection openConnection() throws IOException {
        URLConnection connection = url.openConnection();
        property.entrySet().stream().forEach(e -> connection.setRequestProperty(e.getKey(), e.getValue()));
        connection.setConnectTimeout(timeout);
        return connection;
    }

}
