package hu.domparse.IN3BLK;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;
import java.util.StringJoiner;

public class DomReadIN3BLK {

    public static void main(String[] args) {
        try {
            // XML fájl beolvasása
            File inputFile = new File("XMLTaskIN3BLK\\DOMParseIN3BLK\\XMLIN3BLK.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Mentés fájlba
            File outputFile = new File("XMLTaskIN3BLK\\DOMParseIN3BLK\\ReadOutput.xml");
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true));

            // XML gyökér elemének kiíratása a konzolra és fájlba
            Element rootElement = doc.getDocumentElement();
            String rootName = rootElement.getTagName();
            StringJoiner rootAttributes = new StringJoiner(" ");
            NamedNodeMap rootAttributeMap = rootElement.getAttributes();

            for (int i = 0; i < rootAttributeMap.getLength(); i++) {
                Node attribute = rootAttributeMap.item(i);
                rootAttributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            }

            System.out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n");
            writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

            System.out.print("<" + rootName + " " + rootAttributes.toString() + "> \n");
            writer.print("<" + rootName + " " + rootAttributes.toString() + "> \n");

            NodeList szobaList = doc.getElementsByTagName("Szoba");
            NodeList alkalmazottList = doc.getElementsByTagName("Alkalmazott");
            NodeList hotelList = doc.getElementsByTagName("Hotel");
            NodeList szamlaList = doc.getElementsByTagName("Szamla");
            NodeList vevoList = doc.getElementsByTagName("Vevo");
            NodeList foglalasList = doc.getElementsByTagName("Foglalas");
            NodeList fizetesList = doc.getElementsByTagName("Fizetes");

            // XML kiírása az eredeti formában
            System.out.println("");
            writer.println("");
            printNodeList(szobaList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(alkalmazottList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(hotelList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(szamlaList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(vevoList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(foglalasList, writer);
            System.out.println("");
            writer.println("");
            printNodeList(fizetesList, writer);

            // XML gyökér elemének lezárása
            System.out.println("</" + rootName + ">");
            writer.append("</" + rootName + ">");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // NodeList tartalmának kiírása
    private static void printNodeList(NodeList nodeList, PrintWriter writer) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            printNode(node, 1, writer);
            System.out.println("");
            writer.println("");
        }
    }

    // Node tartalmának kiírása
    private static void printNode(Node node, int indent, PrintWriter writer) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String nodeName = element.getTagName();
            StringJoiner attributes = new StringJoiner(" ");
            NamedNodeMap attributeMap = element.getAttributes();

            for (int i = 0; i < attributeMap.getLength(); i++) {
                Node attribute = attributeMap.item(i);
                attributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            }

            System.out.print(getIndentString(indent));
            System.out.print("<" + nodeName + " " + attributes.toString() + ">");
            writer.print(getIndentString(indent));
            writer.print("<" + nodeName + " " + attributes.toString() + ">");

            NodeList children = element.getChildNodes();
            // Ellenőrzi, hogy az elemnek csak egy szöveges tartalma van-e
            if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
                // Ha csak egy szöveges tartalom van, akkor kiíratja
                System.out.print(children.item(0).getNodeValue());
                writer.print(children.item(0).getNodeValue());
            } else {
                // Ha több gyerek eleme van, akkor új sor karaktereket és behúzást ad hozzá
                System.out.println();
                writer.println();
                for (int i = 0; i < children.getLength(); i++) {
                    printNode(children.item(i), indent + 1, writer);
                }
                System.out.print(getIndentString(indent));
                writer.print(getIndentString(indent));
            }
            System.out.println("</" + nodeName + ">");
            writer.println("</" + nodeName + ">");
        }

    }

    // Egy segédmetódus, amely egy behúzó sztringet hoz létre
    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            // Minden iteráció során két szóközt fűz hozzá a StringBuilderhez.
            sb.append("  ");
        }
        return sb.toString();
    }

}
