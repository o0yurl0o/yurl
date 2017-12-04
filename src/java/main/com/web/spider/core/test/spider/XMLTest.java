package com.web.spider.core.test.spider;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLTest {

	public static void main(String[] args) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
		DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
		factory1.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = factory1.newDocumentBuilder();
		Document doc = builder.parse("src/1.xml");
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		
		
		XPathExpression expr = xpath.compile("//book/title | //book/price");
		
		System.out.println(expr.evaluate(doc,XPathConstants.STRING));
		Object result1 = expr.evaluate(doc,XPathConstants.NODESET);
		NodeList nodes = (NodeList) result1;
		System.out.println(nodes.getLength());
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getNodeName() + "," + nodes.item(i).getNodeValue());
		}
	}

}
