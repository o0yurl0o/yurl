package com.web.spider.core.test.spider;

import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class Test {
	
	public static void main(String [] args) throws XPathExpressionException{
		aa();
	}
	
	public static void aa(){
		 Document doc = null;  
	        try {  
	            doc = Jsoup.connect("https://www.baidu.com").timeout(3000).get();  
	            
//		        System.out.println(doc);
		        
		        W3CDom w3cDom = new W3CDom();
//		        //�����doc����ָ����jsoup���Document����
		        org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(doc);
		        
		        
//	            DocumentBuilderFactory factory1 = DocumentBuilderFactory
//						.newInstance();
//	            factory1.setNamespaceAware(true); // never forget this!
//				DocumentBuilder builder = factory1.newDocumentBuilder();
//				String iuputString = doc.toString();
//				if(iuputString.indexOf("<html")>0){
//					iuputString = iuputString.substring(iuputString.indexOf("<html"), iuputString.length());
//				}
//				org.w3c.dom.Document d = builder.parse(new InputSource(new StringReader(doc.toString())));
				
				
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
	            e.printStackTrace();  
	        }  
//	       
	          
		
	}
	
	

}
