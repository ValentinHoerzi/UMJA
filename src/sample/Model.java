package sample;

import com.sun.org.apache.xpath.internal.operations.Mod;
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

    private static Model model;

    private Model() {
    }

    public static Model INSTANCE(){
        if(Objects.isNull(model)){
            model = new Model();
            model.setParser(new Parser(model));
        }
        return model;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
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

    public void execute(){
        //add error handling here
        Compiler comp = new Compiler();
        comp.compile(parser.parse(new File(filePath_graphml)), filePath_java);
    }

    public void Error(String errorMSG) {
        controller.setErrorMessage(errorMSG);
    }
}