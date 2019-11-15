package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Compiler {

    private List<Clazz> allClazzes;

    public void compile(List<Clazz> allClazzes, String path) {
        List<String> methods = new ArrayList<>();
        List<String> variables = new ArrayList<>();
        for (Clazz clazz : allClazzes) {
            switch (clazz.getStereotype()) { // check if either class, enum or interface must be created
                case "class":
                    createClass(clazz);
                    break;
                case "enumeration":
                    createEnum(clazz);
                    break;
                case "interface":
                    createInterface(clazz);
                    break;
            }
        }
    }

    private void createClass(Clazz clazz) {
        //list with text which gets written in file
        List<String> fileText = new LinkedList<>();

        //package
        fileText.add("package " + clazz.getNameSpace() + ";");

        //imports
        if (clazz.getImports() != null) {
            for (String item : clazz.getImports()) {
                fileText.add("import " + item + ";");
            }
        }

        //class - implementations
        if (clazz.getImplementations() == null || clazz.getImplementations().isEmpty()) {
            fileText.add("public class " + clazz.getName() + "{");
        } else {
            String classLine = "public class " + clazz.getName() + " implements";

            //go through all implementations
            for (int i = 0; i < clazz.getImplementations().size(); i++) {
                // if it is the last implementation no komma but {
                if (i == clazz.getImplementations().size()) {
                    classLine += " " + clazz.getImplementations().get(i) + "{ ";

                } else {
                    classLine += " " + clazz.getImplementations().get(i) + ", ";
                }
            }
            // add the class line to the file
            fileText.add(classLine);
        }

        //final static - SCANNER : Scanner
        // variables
        for (String item : clazz.getVariables()) {
            String[] parts = item.split(" ");
            String variable = "";
            for (int i = 0; i < parts.length - 1; i++) {
                if (parts[i].equals(" final ") || parts[i].equals(" static ")) {
                    variable += parts[i];
                } else if (parts[i].equals("-")) {
                    variable += " private ";
                } else if (parts[i].equals("+")) {
                    variable += " public ";
                } else if(parts[i].equals(":")){
                    variable += parts[i];
                }
            }
        }


// methods

//close class
        fileText.add("}");
    }

    private void createEnum(Clazz clazz) {

    }

    private void createInterface(Clazz clazz) {

    }

    private void writeFile(String path, List<String> fileString) {
        try (FileWriter fileWriter = new FileWriter(path)) {

            for (String line : fileString) {
                fileWriter.write(line);
            }

        } catch
        (IOException ex) {
            ex.printStackTrace();
        }
    }

    private List<String> createMethodFromString(Clazz clazz) {
        return null;
    }

    private List<String> createVariableFromString(Clazz clazz) {
        return null;
    }
}
