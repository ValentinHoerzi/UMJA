package sample;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {

    //private List<Clazz> allClazzes;
    private String path;

    public void compile(List<Clazz> allClazzes, String path) {
        this.path = path;

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
        fileText.add("import sun.reflect.generics.reflectiveObjects.NotImplementedException;");

        //class - implementations
        if (clazz.getImplementations() == null || clazz.getImplementations().isEmpty()) {
            fileText.add("public class " + clazz.getName() + "{");
        } else {
            String classLine = "public class " + clazz.getName() + " implements";

            //go through all implementations
            for (int i = 0; i < clazz.getImplementations().size(); i++) {
                // if it is the last implementation no komma but {
                if (i == clazz.getImplementations().size() - 1) {
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
            if (createVariable(item) != null)
                fileText.add(createVariable(item));
        }

        // methods

        for (String item : clazz.getMetohds()) {
            if (createMethod(item) != null)
                fileText.add(createMethod(item) + "){throw new NotImplementedException();}");
        }

        //close class
        fileText.add("}");

        try {
            writeFile("/Clazz.java", fileText);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String createMethod(String item) {
        try {
            String method = "";
            String[] parts = item.split(" ");
            List<String> list = new LinkedList<String>(Arrays.asList(parts));

            //region public private static

            if (item.contains("+")) {
                method += "public ";
                list.remove("+");
            }
            if (item.contains("-")) {
                method += "private ";
                list.remove("-");
            }
            if (item.contains("static")) {
                method += "static ";
                list.remove("static");
            }
//endregion

            // + Class (user:User, List():list)
            // + Class (user:User, List():list) : String

            if (!parts[parts.length - 1].contains(")")) { // it is no konstruktor
                //region method
                // fügt rückgabetyp der methode dazu zb void, int, String
                method += parts[parts.length - 1];
                list.remove(parts[parts.length - 1]);

                // name der methode mit (
                method += " " + list.get(0).split("\\(")[0] + "(";
                list.set(0, list.get(0).split("\\(")[1]);


                //removing all doublepoints from list
                boolean cont = true;
                while (cont) {
                    cont = list.remove(":");
                }

                //remove last klammer
                list.set(list.size() - 1,
                        list.get(list.size() - 1)
                                .substring(0, list.get(list.size() - 1)
                                        .length() - 1));

                if (list.size() > 2) {
                    for (int i = 1; i < list.size(); i += 2) {
                        method += list.get(i).split(",")[0] + " ";
                        method += list.get(i - 1) + ", ";
                        //user
                        //User,
                    }
                } else if (list.size() > 1) {
                    method += list.get(1).split(",")[0] + " ";
                    method += list.get(0) + ", ";
                }

                //remove last "," and add to filetext
                if (method.contains(","))
                    method = (method.substring(0, method.length() - 2));
                //endregion
            } else { // its a konstruktor
                //region create konstruktor
                // name der methode mit (
                method += " " + list.get(0).split("\\(")[0] + "(";
                list.set(0, list.get(0).split("\\(")[1]);

                if (list.size() > 1) {
                    //removing all doublepoints from list
                    boolean cont = true;
                    while (cont) {
                        cont = list.remove(":");
                    }

                    //remove last klammer
                    list.set(list.size() - 1,
                            list.get(list.size() - 1)
                                    .substring(0, list.get(list.size() - 1)
                                            .length() - 1));

                    if (list.size() > 2) {
                        for (int i = 1; i < list.size(); i += 2) {
                            method += list.get(i).split(",")[0] + " ";
                            method += list.get(i - 1) + ", ";
                            //user
                            //User,
                        }
                    } else if (list.size() > 1) {
                        method += list.get(1).split(",")[0] + " ";
                        method += list.get(0) + ", ";
                    }

                    //remove last "," and add to filetext
                    if (method.contains(","))
                        method = (method.substring(0, method.length() - 2));
                }

                //endregion
            }
            return method;
        } catch (Exception ex) {
            System.out.println("method ->  " + item);
            return null;
        }
    }

    private String createVariable(String item) {
        try {
            String variable = "";

            // add public|static final static
            if (item.contains("+")) {
                variable += "public ";
            }
            if (item.contains("-")) {
                variable += "private ";
            }
            if (item.contains("final")) {
                variable += "final ";
            }
            if (item.contains("static")) {
                variable += "static ";
            }
            String[] parts = item.split(" ");
            if (!item.contains("=")) {
                // add name and type of variable
                variable += parts[parts.length - 1] + " " + parts[parts.length - 3] + ";";
            } else {
                variable += parts[parts.length - 1] + " ";
                String[] foo = item.split("=")[0].split(" ");
                variable += foo[foo.length - 1] + " ";
                String[] baa = item.split("=")[1].split(":");
                variable += "=" + baa[0] + ";";
            }
            return variable;
        } catch (Exception ex) {
            System.out.println("variable ->  " + item);
            return null;
        }
    }

    private void createEnum(Clazz clazz) {
        //list with text which gets written in file
        List<String> fileText = new LinkedList<>();
        //package
        fileText.add("package " + clazz.getNameSpace() + ";");

        fileText.add("public enum " + clazz.getName() + "{");

        for (String item : clazz.getVariables()) {
            item = item.replaceAll("\n", "");
            for (String value : item.split(",")) {
                fileText.add(value.toUpperCase() + ",");
            }
        }
        fileText.set(fileText.size() - 1, fileText.get(fileText.size() - 1).replace(",", ""));
        fileText.add(";}");
        try {
            writeFile("/enum.java", fileText);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createInterface(Clazz clazz) {
        //list with text which gets written in file
        List<String> fileText = new LinkedList<>();
        //package
        fileText.add("package " + clazz.getNameSpace() + ";");

        fileText.add("interface " + clazz.getName() + "{");


        //variable: final static + CAPACITY = 2:int
        for (String item : clazz.getVariables()) {
            if (createVariable(item) != null)
                fileText.add(createVariable(item));
        }

        //method: +getWishlist():Game[]
        // + addToWishlist(game : Game):void

        for (String item : clazz.getMetohds()) {
            if (createMethod(item) != null)
                fileText.add(createMethod(item) + ");");
        }

        //write text to file
        fileText.add("}");
        try {
            writeFile("/interface.java", fileText);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeFile(String pathCustom, List<String> fileString) throws IOException {
        String totalpath = path + pathCustom;
        Files.createDirectories(Paths.get(path));
        try (
                FileWriter writer = new FileWriter(new File(totalpath));
                BufferedWriter fileWriter = new BufferedWriter(writer)
        ) {
            for (String line : fileString) {
                fileWriter.write(line + "\n");
            }
            fileWriter.flush();

        } catch
        (IOException ex) {
            ex.printStackTrace();
        }
    }
}
