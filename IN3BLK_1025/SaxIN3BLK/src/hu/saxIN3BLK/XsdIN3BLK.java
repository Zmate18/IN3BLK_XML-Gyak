package hu.saxIN3BLK;
import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XsdIN3BLK {
    public static void main(String[] args) {

        try {
            File xml = new File("IN3BLK_kurzusfelvetel.xml");
            File xsd = new File("IN3BLK_kurzusfelvetel.xsd");

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsd);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            System.out.println("XSD Validation successful");

        } catch (IOException | SAXException e) {
            System.out.println("Unsuccessful validation");
            System.out.println(e.getMessage());
        }

    }
}