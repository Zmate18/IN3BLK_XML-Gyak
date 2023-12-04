package hu.domparse.IN3BLK;

import org.w3c.dom.*;
import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class DomWriteIN3BLK {

    public static void main(String[] args) {
        try {
            // Dokumentum elkészítése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Gyökér elem létrehozása
            Element rootElement = doc.createElement("IN3BLK_Foglalas");
            rootElement.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xs:noNamespaceSchemaLocation", "XMLSchemaIN3BLK.xsd");
            doc.appendChild(rootElement);

            // Szoba adatok hozzáadása
            addSzoba(doc, rootElement, "1", "2", "36000", "Szabad");
            addSzoba(doc, rootElement, "2", "5", "43000", "Szabad");
            addSzoba(doc, rootElement, "3", "1", "31000", "Foglalt");

            // Alkalmazott adatok hozzáadása
            addAlkalmazott(doc, rootElement, "1", "1", "Tóth András", Arrays.asList("06-70-938-5617"), "320000",
                    "Portás");
            addAlkalmazott(doc, rootElement, "2", "2", "Lakatos Kevin",
                    Arrays.asList("06-30-294-7335", "06-20-993-3744"), "390000", "Szakács");
            addAlkalmazott(doc, rootElement, "3", "3", "Szabó Ilona", Arrays.asList("06-70-223-9485"), "280000",
                    "Takarító");

            // Hotel adatok hozzáadása
            addHotel(doc, rootElement, "1", "1", "Kényelem Hotel", Arrays.asList("06-20-993-7766"), "1028", "Budapest",
                    "Szeles utca 25", "3");
            addHotel(doc, rootElement, "2", "2", "Royal Hotel", Arrays.asList("06-70-234-3443"), "4033", "Debrecen",
                    "Erdei utca 6", "5");
            addHotel(doc, rootElement, "3", "3", "Udvari Hotel", Arrays.asList("06-30-345-2345", "06-30-999-2384"),
                    "3521", "Miskolc", "Jakab utca 56", "4");

            // Számla adatok hozzáadása
            addSzamla(doc, rootElement, "1", "1", "Kiss Anna", "2020-02-17", "86000");
            addSzamla(doc, rootElement, "2", "2", "Török András", "2021-09-10", "113000");
            addSzamla(doc, rootElement, "3", "3", "Horváth Áron", "2022-05-14", "93000");

            // Vevő adatok hozzáadása
            addVevo(doc, rootElement, "1", "1", "Kiss Anna", Arrays.asList("06-30-222-2345", "06-70-399-5577"),
                    "kAnna@gmail.com");
            addVevo(doc, rootElement, "2", "2", "Török András", Arrays.asList("06-20-948-3857", "06-30-947-5872"),
                    "tAndras@freemail.hu");
            addVevo(doc, rootElement, "3", "3", "Horváth Áron", Arrays.asList("06-70-993-6665"), "hAron@gmail.com");

            // Foglalás adatok hozzáadása
            addFoglalas(doc, rootElement, "1", "1", "2020-02-10", "2020-02-17");
            addFoglalas(doc, rootElement, "2", "2", "2021-09-03", "2021-09-10");
            addFoglalas(doc, rootElement, "3", "3", "2022-05-10", "2022-05-14");

            // Fizetés adatok hozzáadása
            addFizetes(doc, rootElement, "1", "1", "2020-02-17", "Készpénz");
            addFizetes(doc, rootElement, "2", "2", "2021-09-10", "Bankkártya");
            addFizetes(doc, rootElement, "3", "3", "2022-05-14", "Bankkártya");

            // Dokumentum mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

            printDocument(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addSzoba(Document doc, Element rooElement, String sz_kod, String emelet, String ar,
            String szabad) {
        // Szoba elem létrehozása
        Element szoba = doc.createElement("Szoba");
        // Szoba elem attribútumainak beállítása
        szoba.setAttribute("sz_kod", sz_kod);

        // Szoba elem gyerek elemeinek létrehozása
        Element emeletElement = createElement(doc, "Emelet", emelet);
        Element arElement = createElement(doc, "Ar", ar);
        Element szabadElement = createElement(doc, "Szabad", szabad);

        // Szoba elem gyerek elemeinek hozzáadása
        szoba.appendChild(emeletElement);
        szoba.appendChild(arElement);
        szoba.appendChild(szabadElement);

        // Szoba elem hozzáadása a gyökér elemhez
        rooElement.appendChild(szoba);
    }

    private static void addAlkalmazott(Document doc, Element rooElement, String a_kod, String h_kod, String nev,
            List<String> telefonok, String ber, String beosztas) {
        Element alkalmazott = doc.createElement("Alkalmazott");
        alkalmazott.setAttribute("a_kod", a_kod);
        alkalmazott.setAttribute("h_kod", h_kod);

        Element nevElement = createElement(doc, "Nev", nev);
        for (String telefon : telefonok) {
            Element telefonElement = createElement(doc, "Telefon", telefon);
            alkalmazott.appendChild(telefonElement);
        }
        Element berElement = createElement(doc, "Ber", ber);
        Element beosztasElement = createElement(doc, "Beosztas", beosztas);

        alkalmazott.appendChild(nevElement);
        alkalmazott.appendChild(berElement);
        alkalmazott.appendChild(beosztasElement);

        rooElement.appendChild(alkalmazott);
    }

    private static void addHotel(Document doc, Element rooElement, String h_kod, String sz_kod, String nev,
            List<String> telefonok, String irszam, String varos, String utca_hazszam, String ertekeles) {
        Element hotel = doc.createElement("Hotel");
        hotel.setAttribute("h_kod", h_kod);
        hotel.setAttribute("sz_kod", sz_kod);

        Element nevElement = createElement(doc, "Nev", nev);
        for (String telefon : telefonok) {
            Element telefonElement = createElement(doc, "Telefon", telefon);
            hotel.appendChild(telefonElement);
        }

        Element cimElement = doc.createElement("Cim");
        Element irszamElement = createElement(doc, "Irszam", irszam);
        Element varosElement = createElement(doc, "Varos", varos);
        Element utca_hazszamElement = createElement(doc, "Utca-hazszam", utca_hazszam);

        Element ertekelesElement = createElement(doc, "Ertekeles", ertekeles);

        cimElement.appendChild(irszamElement);
        cimElement.appendChild(varosElement);
        cimElement.appendChild(utca_hazszamElement);

        hotel.appendChild(nevElement);
        hotel.appendChild(cimElement);
        hotel.appendChild(ertekelesElement);

        rooElement.appendChild(hotel);
    }

    private static void addSzamla(Document doc, Element rooElement, String szam_kod, String v_kod, String nev,
            String datum, String osszeg) {
        Element szamla = doc.createElement("Szamla");
        szamla.setAttribute("szam_kod", szam_kod);
        szamla.setAttribute("v_kod", v_kod);

        Element nevElement = createElement(doc, "Nev", nev);
        Element datumElement = createElement(doc, "Datum", datum);
        Element osszegElement = createElement(doc, "Osszeg", osszeg);

        szamla.appendChild(nevElement);
        szamla.appendChild(datumElement);
        szamla.appendChild(osszegElement);

        rooElement.appendChild(szamla);
    }

    private static void addVevo(Document doc, Element rooElement, String v_kod, String sz_kod, String nev,
            List<String> telefonok, String email) {
        Element vevo = doc.createElement("Vevo");
        vevo.setAttribute("v_kod", v_kod);
        vevo.setAttribute("sz_kod", sz_kod);

        Element nevElement = createElement(doc, "Nev", nev);
        for (String telefon : telefonok) {
            Element telefonElement = createElement(doc, "Telefon", telefon);
            vevo.appendChild(telefonElement);
        }
        Element emailElement = createElement(doc, "Email", email);

        vevo.appendChild(nevElement);
        vevo.appendChild(emailElement);

        rooElement.appendChild(vevo);
    }

    private static void addFoglalas(Document doc, Element rooElement, String sz_kod, String v_kod, String kezdet,
            String vege) {
        Element foglalas = doc.createElement("Foglalas");
        foglalas.setAttribute("sz_kod", sz_kod);
        foglalas.setAttribute("v_kod", v_kod);

        Element kezdetElement = createElement(doc, "Kezdet", kezdet);
        Element vegeElement = createElement(doc, "Vege", vege);

        foglalas.appendChild(kezdetElement);
        foglalas.appendChild(vegeElement);

        rooElement.appendChild(foglalas);
    }

    private static void addFizetes(Document doc, Element rooElement, String v_kod, String szam_kod, String datum,
            String fizetesmod) {
        Element fizetes = doc.createElement("Fizetes");
        fizetes.setAttribute("v_kod", v_kod);
        fizetes.setAttribute("szam_kod", szam_kod);

        Element datumElement = createElement(doc, "Datum", datum);
        Element fizetesmodElement = createElement(doc, "Fizetesmod", fizetesmod);

        fizetes.appendChild(datumElement);
        fizetes.appendChild(fizetesmodElement);

        rooElement.appendChild(fizetes);
    }

    // Dokumentum kiírása
    private static void printDocument(Document doc) {
        try {
            // Mentés fájlba
            File outputFile = new File("XMLTaskIN3BLK\\DOMParseIN3BLK\\XMLIN3BLK1.xml");
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

    // NodeList kiírása
    private static void printNodeList(NodeList nodeList, PrintWriter writer) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            printNode(node, 1, writer);
            System.out.println("");
            writer.println("");
        }
    }

    // Node kiírása
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

    private static Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

    // Behúzások létrehozása
    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            // Minden iteráció során két szóközt fűz hozzá a StringBuilderhez
            sb.append("  ");
        }
        return sb.toString();
    }

}
