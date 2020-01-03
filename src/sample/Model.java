package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.java2d.pipe.SolidTextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Model {
    private Controller controller;
    public Parser parser;

    private String filePath_graphml;
    private String filePath_java;


    public Model(Controller controller) {
        this.controller = controller;
        parser = new Parser(this);

        Compiler comp = new Compiler();
        comp.compile(parser.parse(new File("D:/Dokumente_D/Google Drive/4AHIF/AUD/UMJA/uml.graphml")), "D:/Desktop_D/bluejProj/test");
    }

    public String getFilePath_java() {
        return filePath_java;
    }

    public void setFilePath_java(String filePath_java) {
        this.filePath_java = filePath_java;
    }

    public String getFilePath_graphml() {
        return filePath_graphml;
    }

    public void setFilePath_graphml(String filePath_graphml) {
        this.filePath_graphml = filePath_graphml;
    }

    public void Error(String errorMSG) {
        controller.setErrorMessage(errorMSG);
    }

}