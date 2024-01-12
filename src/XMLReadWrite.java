import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class XMLReadWrite {
    public static void read(ArrayList<String> arr) {
            try {
                File file = new File("input.xml");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("equation");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        System.out.println(element.getTextContent());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static void write(ArrayList<Integer> result) {
        try {
            FileWriter fileWriter = new FileWriter("output.xml");
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
            fileWriter.write("<EquationAnswer>\n");
            for (Integer answer : result) {
                fileWriter.write("    <answer>" + answer + "</answer>\n");
            }
            fileWriter.write("</EquationAnswer>");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}