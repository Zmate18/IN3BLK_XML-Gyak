package domIN3BLK1108;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DOMReadIN3BLK {
	public static void main(String[] args) {
        try {
            File inputFile = new File("orarendIN3BLK.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("ora");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nAktuális elem :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Id : " + eElement.getAttribute("id"));
                    System.out.println("Tipus : " + eElement.getAttribute("tipus"));
                    System.out.println("Targy : " + eElement.getElementsByTagName("targy").item(0).getTextContent());
                    Element idopont = (Element) eElement.getElementsByTagName("idopont").item(0);
                    System.out.println("Nap : " + idopont.getElementsByTagName("nap").item(0).getTextContent());
                    System.out.println("Tol : " + idopont.getElementsByTagName("tol").item(0).getTextContent());
                    System.out.println("Ig : " + idopont.getElementsByTagName("ig").item(0).getTextContent());
                    System.out.println("Helyszin : " + eElement.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("Oktato : " + eElement.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("Szak : " + eElement.getElementsByTagName("szak").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
