package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Compiler {

    private List<Clazz> allClazzes;

    public void compile(List<Clazz> allClazzes, String path) {
        List<String> methods = new ArrayList<>();
        List<String> variables = new ArrayList<>();
        for (Clazz clazz : allClazzes) {
            methods = createMethodFromString(clazz);
            variables = createVariableFromString(clazz);
        }
        variables.addAll(methods);// add all methods to the variable collection so the file is complete and no further collection has to be created 
        writeFile(path, variables);
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
