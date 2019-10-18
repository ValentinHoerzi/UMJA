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
         switch(clazz.getStereotype())  { // check if either class, enum or interface must be created
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

	private void createClass(Clazz clazz){
		
	}

	private void createEnum(Clazz clazz){
		
	}

	private void createInterface(Clazz clazz){
		
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
