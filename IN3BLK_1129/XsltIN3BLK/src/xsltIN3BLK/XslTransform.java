package xsltKLNSPG;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XslTransform {


	    public static void main(String[] args) {
	        try {
	        	//1. feladat
	            String xmlInput = "hallgatoIN3BLK.xml";
	            String xsltInputHTML = "hallgatoIN3BLK.xsl";
	            String xsltInputXML = "hallgatoIN3BLKxml.xsl";
	            String outputResult = "hallgatoIN3BLK.html";
	            String outputResultXML = "hallgatoIN3BLK.out.xml";

	            TransformerFactory transformerFactory = TransformerFactory.newInstance();

	            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltInputHTML));
	            transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResult));
	            
	            transformer = transformerFactory.newTransformer(new StreamSource(xsltInputXML));
	            transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResultXML));
	            
	            //2. feladat
	            xmlInput = "orarendIN3BLK.xml";
	            xsltInputHTML = "orarendIN3BLK.xsl";
	            xsltInputXML = "orarendIN3BLKxml.xsl";
	            outputResult = "orarendIN3BLK.html";
	            outputResultXML = "orarendIN3BLK.out.xml";

	            transformer = transformerFactory.newTransformer(new StreamSource(xsltInputHTML));
	            transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResult));
	            
	            transformer = transformerFactory.newTransformer(new StreamSource(xsltInputXML));
	            transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResultXML));

	            System.out.println("Sikeres XSLT transzformáció, eredmény mentve.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
