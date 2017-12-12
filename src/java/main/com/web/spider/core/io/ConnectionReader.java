package com.web.spider.core.io;

import com.web.spider.core.connection.ParamsURL;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yurl on 2017/12/12.
 */
public class ConnectionReader {

    private String charset = "utf-8";
    private ParamsURL url;
    private Map<String, List<String>> headerProperties = new HashMap<String, List<String>>();
    private String context = "";

    public ConnectionReader(ParamsURL url) {
        this.url = url;
    }

    public List<String> getRequestHeaderValue(String key) {
        return headerProperties.get(key);
    }

    public void readURL() throws IOException {
        URLConnection connection = url.openConnection();
        connection.connect();
        headerProperties = connection.getHeaderFields();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            context = result;
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public org.w3c.dom.Document parseDoc() {
        org.jsoup.nodes.Document doc = null;
        doc = Jsoup.parse(context);
        W3CDom w3cDom = new W3CDom();
        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(doc);
        return w3cDoc;
    }

}
