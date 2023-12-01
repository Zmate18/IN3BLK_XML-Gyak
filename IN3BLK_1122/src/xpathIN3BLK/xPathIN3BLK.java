package xpathIN3BLK;

import org.w3c.dom.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class xPathIN3BLK {

	public static void main(String[] args) {
		try {
			File inputFile = new File("studentIN3BLK.xml");
			// Dokumentum elk�sz�t�se
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(inputFile);
			doc.getDocumentElement().normalize();

			// xPath k�sz�t�se
			XPath xPath = XPathFactory.newInstance().newXPath();

			System.out.println("1.");
			String IN3BLK = "class/student";
			NodeList nodeList = (NodeList) xPath.compile(IN3BLK).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList);

			System.out.println("\n2.");
			String IN3BLK_2 = "class/student[@id='2']";
			NodeList nodeList2 = (NodeList) xPath.compile(IN3BLK_2).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList2);

			System.out.println("\n3.");
			String IN3BLK_3 = "//student";
			NodeList nodeList3 = (NodeList) xPath.compile(IN3BLK_3).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList3);

			System.out.println("\n4.");
			String IN3BLK_4 = "/class/student[2]";
			NodeList nodeList4 = (NodeList) xPath.compile(IN3BLK_4).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList4);

			System.out.println("\n5.");
			String IN3BLK_5 = "/class/student[last()]";
			NodeList nodeList5 = (NodeList) xPath.compile(IN3BLK_5).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList5);

			System.out.println("\n6.");
			String IN3BLK_6 = "/class/student[last()-1]";
			NodeList nodeList6 = (NodeList) xPath.compile(IN3BLK_6).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList6);

			System.out.println("\n8.");
			String IN3BLK_8 = "/class/*";
			NodeList nodeList8 = (NodeList) xPath.compile(IN3BLK_8).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList8);

			System.out.println("\n9.");
			String IN3BLK_9 = "/class/student[@*]";
			NodeList nodeList9 = (NodeList) xPath.compile(IN3BLK_9).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList9);

			System.out.println("\n10.");
			String IN3BLK_10 = "/*/*";
			NodeList nodeList10 = (NodeList) xPath.compile(IN3BLK_10).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList10);

			System.out.println("\n11.");
			String IN3BLK_11 = "/class/student[kor > 20]";
			NodeList nodeList11 = (NodeList) xPath.compile(IN3BLK_11).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList11);

			System.out.println("\n12.");
			String IN3BLK_12 = "/class/student[keresztnev]";
			NodeList nodeList12 = (NodeList) xPath.compile(IN3BLK_12).evaluate(doc, XPathConstants.NODESET);
			writeData(nodeList12);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeData(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			System.out.println("\nAktu�lis elem: " + node.getNodeName());

			if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
				Element element = (Element) node;

				System.out.println("Hallgat� ID: " + element.getAttribute("id"));

				System.out
						.println("Vezet�kn�v: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
				System.out
						.println("Keresztn�v: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());

				System.out.println("Becen�v : " + element.getElementsByTagName("becenev").item(0).getTextContent());

				System.out.println("Kor : " + element.getElementsByTagName("kor").item(0).getTextContent());

			}

		}

	}

}
