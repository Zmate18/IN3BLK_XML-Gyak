package hu.domparse.IN3BLK;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DomQueryIN3BLK {

	public static void main(String[] args) {
		try {
			// XML fájl beolvasáss
			File inputFile = new File("IN3BLK_XML-Gyak\\XMLTaskIN3BLK\\2. Feladat\\DOMParseIN3BLK\\XMLIN3BLK.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
