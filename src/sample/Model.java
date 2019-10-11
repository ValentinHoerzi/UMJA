package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private Controller controller;

    public Model(Controller controller) {
        this.controller = controller;
    }

    public List<Clazz> parse(File file) {
        List<Clazz> clazzes = new ArrayList<>();
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("node");


                Node nNode = nList.item(0);
                    System.out.println("package-name : " + ((Element)nList.item(0)).getElementsByTagName("y:NodeLabel").item(0).getTextContent().trim());
            System.out.println("class-name : " + ((Element)nList.item(1)).getElementsByTagName("y:NodeLabel").item(0).getTextContent().trim());
            System.out.println("variablen : " + ((Element)nList.item(1)).getElementsByTagName("y:AttributeLabel").item(0).getTextContent().trim());
            System.out.println("methods : " + ((Element)nList.item(1)).getElementsByTagName("y:MethodLabel").item(0).getTextContent().trim());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazzes;
    }
}