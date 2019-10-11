package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Compiler {

    private List<Clazz> allClazzes;

    public void compile(List<Clazz> allClazzes) {
        try (FileWriter fileWriter = new FileWriter("test.java")) {

            fileWriter.write("public class foobar");

        } catch
        (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void writeFile(String path) {
        try (FileWriter fileWriter = new FileWriter(path)) {

            fileWriter.write("public class foobar");

        } catch
        (IOException ex) {
            ex.printStackTrace();
        }
    }

    private List<String> createClassListStrings(Clazz clazz) {
        List<String> confusingStrings = clazz.getMetohds();
        List<String> methodStrings = new ArrayList<>();
        for (String line : confusingStrings) {
            methodStrings.add(createClassFromString(line));
        }

        return null;
    }

    private String createClassFromString(String confusingLine) {
        return null;
    }
}
