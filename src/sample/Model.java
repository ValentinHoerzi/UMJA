package sample;

import java.io.File;
import java.util.Objects;

public class Model {
    private static Model model;
    public Parser parser;
    private Controller controller;
    private String filePath_graphml;

    private String filePath_java;

    private Model() {
    }

    public static Model INSTANCE() {
        if (Objects.isNull(model)) {
            model = new Model();
            model.setParser(new Parser(model));
        }
        return model;
    }

    public void execute() {
        //add error handling here
        Compiler comp = new Compiler();
        if ((Objects.isNull(filePath_graphml) || filePath_graphml.equals(" ")) || (Objects.isNull(filePath_java) || filePath_java.equals(" "))) {
            throwErrorMessage("No UML file delivered");
            return;
        }
        comp.compile(parser.parse(new File(filePath_graphml)), filePath_java);
    }

    public void throwErrorMessage(String errorMessage) {
        controller.setErrorMessage(errorMessage);
        System.err.println(errorMessage);
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setFilePath_java(String filePath_java) {
        this.filePath_java = filePath_java;
    }

    public void setFilePath_graphml(String filePath_graphml) {
        this.filePath_graphml = filePath_graphml;
    }
}