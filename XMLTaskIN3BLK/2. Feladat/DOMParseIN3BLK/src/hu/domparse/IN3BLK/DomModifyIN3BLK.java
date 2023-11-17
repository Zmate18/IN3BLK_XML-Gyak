package hu.domparse.IN3BLK;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.*;

public class DomModifyIN3BLK {

	public static void main(String[] args) {
		try {
			// XML fájl beolvasáss
			File inputFile = new File("XMLIN3BLK.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			// Adatok módosítása
			// Alkalmazott bérének módosítása
			// Egy adott típus összes elemét lekérjük és eltároljuk egy listába
			NodeList alkalmazottList = doc.getElementsByTagName("Alkalmazott");
			// Index alapján lekérjük azt az elemet, amelyiket módosítani szeretnénk
			Element alkalmazott = (Element) alkalmazottList.item(0);
			// Megkeressük az elemben a módosítani kivánt tag-et, majd beállítjuk az új
			// tartalmát (setTextContent)
			alkalmazott.getElementsByTagName("Ber").item(0).setTextContent("400000");

			// Hotel telefonszámának módosítása
			NodeList hotelList = doc.getElementsByTagName("Hotel");
			Element hotel = (Element) hotelList.item(1);
			hotel.getElementsByTagName("Telefon").item(0).setTextContent("06-20-334-4977");

			// Vevő Email címének módosítása
			NodeList vevoList = doc.getElementsByTagName("Vevo");
			Element vevo = (Element) vevoList.item(2);
			vevo.getElementsByTagName("Email").item(0).setTextContent("horvat.aron@gmail.com");

			// Foglalás végének időpontjának módosítása
			NodeList foglalasList = doc.getElementsByTagName("Foglalas");
			Element foglalas = (Element) foglalasList.item(0);
			foglalas.getElementsByTagName("Vege").item(0).setTextContent("2020-02-23");

			// Fizetés módjának módosítása
			NodeList fizetesList = doc.getElementsByTagName("Fizetes");
			Element fizetes = (Element) fizetesList.item(2);
			fizetes.getElementsByTagName("Fizetesmod").item(0).setTextContent("Készpénz");

			// Konzolra való kiíratás
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
