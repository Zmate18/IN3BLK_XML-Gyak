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
			 NodeList nodeList = (NodeList) xPath.compile(IN3BLK).evaluate( doc, XPathConstants.NODESET);
			 writeData(nodeList);
			 
			 System.out.println("\n2.");
			 String IN3BLK_2 = "class/student[@id='2']";
			 NodeList nodeList2 = (NodeList) xPath.compile(IN3BLK_2).evaluate( doc, XPathConstants.NODESET);
			 writeData(nodeList2);
			 
			 System.out.println("\n3.");
			 String IN3BLK_3 = "//student";
			 NodeList nodeList3 = (NodeList) xPath.compile(IN3BLK_3).evaluate( doc, XPathConstants.NODESET);
			 writeData(nodeList3);
			 
	        
	        
		} catch (Exception e) {
	        e.printStackTrace();
	    }
	}
		public static void writeData(NodeList nodeList) {
			  for (int i = 0; i < nodeList.getLength(); i++) {
		            Node node = nodeList.item(i);
			  
		            System.out.println("\nAktu�lis elem: " + node.getNodeName());
			  
		            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
		            	Element element = (Element)node;
		            	
		            	
		            	 System.out.println("Hallgat� ID: "+ element.getAttribute("id"));
		            	 
		            	 System.out.println("Vezet�kn�v: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
		            	 System.out.println("Keresztn�v: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());

		                 System.out.println("Becen�v : " + element.getElementsByTagName("becenev").item(0).getTextContent());

		                 System.out.println("Kor : " + element.getElementsByTagName("kor").item(0).getTextContent());
		            
		            }
		            
		          
			  } 

	}

}
