package hu.domparse.IN3BLK;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DomQueryIN3BLK {

	public static void main(String[] args) {
		try {
			// XML fájl beolvasása
			File inputFile = new File("XMLTaskIN3BLK\\DOMParseIN3BLK\\XMLIN3BLK.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			// 1. Lekérdezés: kiírja az 1-es ID-val rendelkező hotel adatait
			// Hotel elemek lekérdezése
            NodeList hotelList = doc.getElementsByTagName("Hotel");
			System.out.println("1. Lekérdezés:");

            for (int i = 0; i < hotelList.getLength(); i++) {
                Element hotelElement = (Element) hotelList.item(i);
                String h_kod = hotelElement.getAttribute("h_kod");

                if ("1".equals(h_kod)) {
                    System.out.println("Az 1-es ID-val rendelkező hotel adatai:");
                    System.out.println("Név: " + hotelElement.getElementsByTagName("Nev").item(0).getTextContent());
                    System.out.println("Telefon: " + hotelElement.getElementsByTagName("Telefon").item(0).getTextContent());

                    Element cimElement = (Element) hotelElement.getElementsByTagName("Cim").item(0);
                    if (cimElement != null) {
                        System.out.println("Cím: " +
                                cimElement.getElementsByTagName("Irszam").item(0).getTextContent() + ", " +
                                cimElement.getElementsByTagName("Varos").item(0).getTextContent() + ", " +
                                cimElement.getElementsByTagName("Utca-hazszam").item(0).getTextContent());
                    }

                    System.out.println("Értékelés: " + hotelElement.getElementsByTagName("Ertekeles").item(0).getTextContent());
                    break;  // Kilépü a ciklusból, mert megtaláltuk az első illeszkedő hotelt
                }
            }

			// 2. Lekérdezés: kiírja azoknak az alkalmazottaknak a nevét, akiknek a bére több, mint 300000Ft
			// Alkalmazott elemek lekérdezése
            NodeList alkalmazottList = doc.getElementsByTagName("Alkalmazott");
			System.out.println("\n2. Lekérdezés:");

            System.out.println("Alkalmazottak, akiknek a bére több, mint 300000Ft:");
            for (int i = 0; i < alkalmazottList.getLength(); i++) {
                Element alkalmazottElement = (Element) alkalmazottList.item(i);
                int ber = Integer.parseInt(alkalmazottElement.getElementsByTagName("Ber").item(0).getTextContent());

                if (ber > 300000) {
                    String nev = alkalmazottElement.getElementsByTagName("Nev").item(0).getTextContent();
                    System.out.println(nev);
                }
            }

			// 3. Lekérdezés: kiírja azoknak a Vevőknek az adatait akik a Royal Hotelben foglaltak szobát
			// Hotel elemek lekérdezése
            NodeList hotelListRoyal = doc.getElementsByTagName("Hotel");
			System.out.println("\n3. Lekérdezés:");

            System.out.println("Royal Hotelhez tartozó vevők adatai:");
            for (int i = 0; i < hotelListRoyal.getLength(); i++) {
                Element hotelElement = (Element) hotelListRoyal.item(i);
                String hotelNev = hotelElement.getElementsByTagName("Nev").item(0).getTextContent();

                if ("Royal Hotel".equals(hotelNev)) {
                    String hotelKod = hotelElement.getAttribute("h_kod");

                    // Vevo elemek lekérdezése a megfelelő hotel kód alapján
                    NodeList vevoList = doc.getElementsByTagName("Vevo");
                    for (int j = 0; j < vevoList.getLength(); j++) {
                        Element vevoElement = (Element) vevoList.item(j);
                        String vevoSzKod = vevoElement.getAttribute("sz_kod");

                        if (vevoSzKod.equals(hotelKod)) {
                            System.out.println("Név: " + vevoElement.getElementsByTagName("Nev").item(0).getTextContent());
                            System.out.println("Telefon: " + vevoElement.getElementsByTagName("Telefon").item(0).getTextContent());
                            System.out.println("Email: " + vevoElement.getElementsByTagName("Email").item(0).getTextContent());
                        }
                    }
                }
            }

			// 4. lekérdezés: kiírja Kiss Anna számlázási adatait, azaz a szálloda nevét, a szoba emeletét, a foglalás kezdetét és végét, valamint a fizetendő összeget
			// Vevő elemek lekérdezése "Kiss Anna" név alapján
            NodeList vevoList = doc.getElementsByTagName("Vevo");
			System.out.println("\n4. Lekérdezés:");

            for (int i = 0; i < vevoList.getLength(); i++) {
                Element vevoElement = (Element) vevoList.item(i);
                String vevoName = vevoElement.getElementsByTagName("Nev").item(0).getTextContent();

                if ("Kiss Anna".equals(vevoName)) {
                    String customerSzKod = vevoElement.getAttribute("sz_kod");

                    // Hotel, Szoba, Foglalas és Szamla elemek lekérdezése a vevő kódja alapján
                    NodeList hotelListSzamlazas = doc.getElementsByTagName("Hotel");
                    for (int j = 0; j < hotelListSzamlazas.getLength(); j++) {
                        Element hotelElement = (Element) hotelListSzamlazas.item(j);
                        String hotelKod = hotelElement.getAttribute("h_kod");

                        if (hotelKod.equals(customerSzKod)) {
                            System.out.println("Kiss Anna számlázási adatai:");
                            System.out.println("Hotel neve: " + hotelElement.getElementsByTagName("Nev").item(0).getTextContent());

                            // Szoba és Foglalas elemek lekérdezése a szálloda kódja alapján
                            NodeList szobaList = doc.getElementsByTagName("Szoba");
                            for (int k = 0; k < szobaList.getLength(); k++) {
                                Element szobaElement = (Element) szobaList.item(k);
                                String szobaSzKod = szobaElement.getAttribute("sz_kod");

                                if (szobaSzKod.equals(hotelKod)) {
                                    System.out.println("Szoba emelete: " + szobaElement.getElementsByTagName("Emelet").item(0).getTextContent());

                                    // Foglalas elemek lekérdezése a szoba kódja alapján
                                    NodeList foglalasList = doc.getElementsByTagName("Foglalas");
                                    for (int l = 0; l < foglalasList.getLength(); l++) {
                                        Element foglalasElement = (Element) foglalasList.item(l);
                                        String foglalasSzKod = foglalasElement.getAttribute("sz_kod");

                                        if (foglalasSzKod.equals(szobaSzKod)) {
                                            System.out.println("Foglalás kezdete: " + foglalasElement.getElementsByTagName("Kezdet").item(0).getTextContent());
                                            System.out.println("Foglalás vége: " + foglalasElement.getElementsByTagName("Vege").item(0).getTextContent());
                                        }
                                    }

                                    // Szamla elemek lekérdezése a vevő kódja alapján
                                    NodeList szamlaList = doc.getElementsByTagName("Szamla");
                                    for (int m = 0; m < szamlaList.getLength(); m++) {
                                        Element szamlaElement = (Element) szamlaList.item(m);
                                        String szamlaVKod = szamlaElement.getAttribute("v_kod");

                                        if (szamlaVKod.equals(customerSzKod)) {
                                            System.out.println("Fizetendő összeg: " + szamlaElement.getElementsByTagName("Osszeg").item(0).getTextContent());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

			// 5. lekérdezés: kiírja azoknak a vevőknek a nevét, akik bankkártyával fizettek, valamint a fizetés dátumát
			// Fizetes elemek lekérdezése Bankkártya fizetésmóddal
            NodeList fizetesList = doc.getElementsByTagName("Fizetes");
			System.out.println("\n5. Lekérdezés:");

            System.out.println("Vevők, akik Bankkártyával fizettek:");
            for (int i = 0; i < fizetesList.getLength(); i++) {
                Element fizetesElement = (Element) fizetesList.item(i);
                String fizetesmod = fizetesElement.getElementsByTagName("Fizetesmod").item(0).getTextContent();

                if ("Bankkártya".equals(fizetesmod)) {
                    String vevoFizetesVKod = fizetesElement.getAttribute("v_kod");

                    // Vevo elemek lekérdezése a vevő kódja alapján
                    NodeList vevokList = doc.getElementsByTagName("Vevo");
                    for (int j = 0; j < vevokList.getLength(); j++) {
                        Element vevoElement = (Element) vevokList.item(j);
                        String vevoVKod = vevoElement.getAttribute("v_kod");

                        if (vevoVKod.equals(vevoFizetesVKod)) {
                            System.out.println("Vevő neve: " + vevoElement.getElementsByTagName("Nev").item(0).getTextContent());

                            // Fizeteshez tartozó Dátum lekérdezése
                            System.out.println("Fizetés dátuma: " + fizetesElement.getElementsByTagName("Datum").item(0).getTextContent() + "\n");
                        }
                    }
                }
            }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
