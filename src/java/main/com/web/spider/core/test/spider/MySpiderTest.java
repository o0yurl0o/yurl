package com.web.spider.core.test.spider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Created by yurl on 2017/12/4.
 */
public class MySpiderTest {

    public static void main(String[] args) {
        bb();

    }

    public static void bb(){

        BufferedReader in = null;
        String match = "a[href]";
        try {
            String urlNameString = "https://www.baidu.com";
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/5.0 (Windows NT 6.1; ");
////                    + "WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
//            	System.out.println(line);
                result += line;
            }


            org.jsoup.nodes.Document doc = null;
            doc = Jsoup.parse(result);
//	        System.out.println(doc);

            W3CDom w3cDom = new W3CDom();
//	        //这里的doc对象指的是jsoup里的Document对象
            org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(doc);

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile("//a[@href]");
            Object result1 = expr.evaluate(w3cDoc, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result1;
            System.out.println(nodes.getLength());
            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println("text:" + nodes.item(i).getTextContent() + "name:" + nodes.item(i).getNodeName() + ",value:" + nodes.item(i).getNodeValue());
            }




        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public static void aa() {

        InputStream in = null;
        String match = "a[href]";
        try {
            String urlNameString = "http://www.baidu.com";
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection
                    .setRequestProperty(
                            "user-agent",
                            "Mozilla/5.0 (Windows NT 6.1;"// );//WOW64)
                                    // AppleWebKit/537.36
                                    // (KHTML, like
                                    // Gecko)
                                    // Chrome/49.0.2623.112");
                                    + "WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
            // 建立实际的连接
            connection.connect();
            String s = "";
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            // 定义 BufferedReader输入流来读取URL的响应
            in = connection.getInputStream();

            // while ((line = in.readLine()) != null) {
            // result += line;
            // System.out.println(line);
            // }
            DocumentBuilderFactory factory1 = DocumentBuilderFactory
                    .newInstance();

            factory1.setNamespaceAware(true); // never forget this!
            DocumentBuilder builder = factory1.newDocumentBuilder();
            Document doc = builder.parse(in);

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = xpath.compile("a[href]");
            Object result1 = expr.evaluate(doc, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result1;
            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println(nodes.item(i).getNodeValue());
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

}
