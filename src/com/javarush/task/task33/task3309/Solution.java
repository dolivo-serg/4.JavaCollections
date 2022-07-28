package com.javarush.task.task33.task3309;

/*
Комментарий внутри xml
*/


import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, ParserConfigurationException, TransformerException, IOException, SAXException {


        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        Writer stringWriter = new StringWriter();
        marshaller.marshal(obj, new XMLSerializer(stringWriter, null) {

            private final Pattern CDATA_CHARS = Pattern.compile("[<>&\"\']");

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                boolean hasCDATA = CDATA_CHARS.matcher(new String(ch, start, length)).find();
                if (hasCDATA) super.startCDATA();
                super.characters(ch, start, length);
                if (hasCDATA) super.endCDATA();
            }
        });

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = documentBuilderFactory.newDocumentBuilder().parse
                (new InputSource(new StringReader(stringWriter.toString())));

        NodeList nodeList = document.getElementsByTagName("*");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeName().equals(tagName)) {
                currentNode.getParentNode().insertBefore(document.createComment(comment), currentNode);
                currentNode.getParentNode().insertBefore(document.createTextNode("\n"), currentNode);
            }
        }

        stringWriter = new StringWriter();

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));

        return stringWriter.toString();
    }




    public static void main(String[] args) throws JAXBException, TransformerException, ParserConfigurationException, IOException, SAXException {

        Test test = new Test();
        test.setId(1);
        test.setField1(new ArrayList<>());
        test.getField1().add("element 1");
        test.getField1().add("<![CDATA[need CDATA because of <field1>]]>");
        test.setField2("It is field 2");

        System.out.println(toXmlWithComment(test, "field1", "it's a comment"));

    }

    @XmlRootElement
    private static class Test {
        int id;
        List<String> field1;
        String field2;

        public int getId() {
            return id;
        }

        public List<String> getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }

        @XmlElement
        public void setId(int id) {
            this.id = id;
        }

        @XmlElement
        public void setField1(List<String> field1) {
            this.field1 = field1;
        }

        @XmlElement
        public void setField2(String field2) {
            this.field2 = field2;
        }
    }


}

