package Sandbox.ZipExtractor;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtils {

     public static <T> String marshal(T object) throws JAXBException {
        final Marshaller   marshaller   = JAXBContext.newInstance(object.getClass()).createMarshaller();
        final StringWriter stringWriter = new StringWriter();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, stringWriter);

        return stringWriter.toString();
    }


    public static <T> T unMarshal(String content, Class<T> clazz) throws JAXBException {
        JAXBContext  jaxbContext  = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return unmarshaller.unmarshal(new StreamSource(new StringReader(content)), clazz).getValue();
    }


    public static String convertDocumentToString(Document doc)
            throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer        transformer        = transformerFactory.newTransformer();

        // Unquote below to remove XML declaration.
        // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        StringWriter writer = new StringWriter();

        transformer.transform(new DOMSource(doc), new StreamResult(writer));

        String output = writer.getBuffer().toString();

        return output;
    }


    public static Document convertStringToDocument(String xmlStr)
            throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
        DocumentBuilder        builder  = factory.newDocumentBuilder();

        return builder.parse(new InputSource(new StringReader(xmlStr)));
    }
}
