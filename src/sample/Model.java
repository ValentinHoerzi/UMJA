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

    public Model(Controller controller) {
        this.controller = controller;

        Compiler comp = new Compiler();
        comp.compile(parse(new File("D:/Dokumente_D/Google Drive/4AHIF/AUD/UMJA/uml.graphml")), "D:/Desktop/UMJAResult/test");
    }

    public List<Clazz> parse(File file) {
        List<Clazz> clazzes = new ArrayList<>();
        Map<String, String[]> idToName = new TreeMap<>();

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeListClasses = doc.getElementsByTagName("node");
            String nameSpace = null;
            for (int i = 0; i < nodeListClasses.getLength(); i++) {
                Clazz.Builder clazzBuilder = Clazz.Builder.aClazz();

                String id = ((Element) nodeListClasses.item(i)).getAttribute("id");
                Element element = ((Element) nodeListClasses.item(i));
                if (!id.contains(":")) {
                    nameSpace = element.getElementsByTagName("y:NodeLabel").item(0).getTextContent().trim();
                } else {
                    clazzBuilder.withNameSpace(nameSpace);

                    String name = element.getElementsByTagName("y:NodeLabel").item(0).getTextContent().trim();
                    clazzBuilder.withName(name);
                    idToName.put(id, new String[]{nameSpace, name});

                    String stereotype = element.getElementsByTagName("y:UML").item(0).getAttributes().getNamedItem("stereotype").getTextContent();
                    clazzBuilder.withStereotype(stereotype.equals("") ? "class" : stereotype);

                    String variables = removeUnnecessaryHTML(element.getElementsByTagName("y:AttributeLabel").item(0).getTextContent().trim());

                    List<String> variables_list = stereotype.equals("enumeration")
                            ? Arrays.asList(variables)
                            : Arrays.asList(variables.split("\n"));
                    variables_list = variables_list.stream().filter(str -> str.length() > 3).collect(Collectors.toList());
                    for (int idx = 0; idx < variables_list.size(); idx++) {
                        variables_list.set(idx, addStaticIfNeeded(variables_list.get(idx)));
                    }
                    if (!stereotype.equals("enumeration")) {
                        for (int idx = 0; idx < variables_list.size(); idx++) {
                            variables_list.set(idx, addFinalIfNeeded(variables_list.get(idx)));
                        }
                    }
                    clazzBuilder.withVariables(variables_list);

                    if (!stereotype.equals("enumeration")) {
                        String methods = removeUnnecessaryHTML(element.getElementsByTagName("y:MethodLabel").item(0).getTextContent().trim());
                        List<String> methods_list = Arrays.asList(methods.split("\n"));
                        methods_list = methods_list.stream().filter(str -> str.length() > 3).collect(Collectors.toList());
                        for (int idx = 0; idx < methods_list.size(); idx++) {
                            methods_list.set(idx, addStaticIfNeeded(methods_list.get(idx)));
                        }
                        clazzBuilder.withMetohds(methods_list);
                    }
                    clazzes.add(clazzBuilder.build());
                }
            }
            NodeList nodeListReferences = doc.getElementsByTagName("edge");
            for (int i = 0; i < nodeListReferences.getLength(); i++) {
                Element edge = ((Element) nodeListReferences.item(i));
                String srcId = edge.getAttribute("source");
                String trgtId = edge.getAttribute("target");

                String arrowLine = ((Element) edge.getElementsByTagName("y:LineStyle").item(0)).getAttribute("type");
                String arrowHead = ((Element) edge.getElementsByTagName("y:Arrows").item(0)).getAttribute("target");

                if (arrowLine.equals("dashed") && arrowHead.equals("white_delta")) {
                    clazzes.stream()
                            .filter(clazz -> clazz.getNameSpace().equals(idToName.get(srcId)[0]) && clazz.getName().equals(idToName.get(srcId)[1]))
                            .forEach(clazz -> {
                                clazz.addImplementation(idToName.get(trgtId)[1]);
                                clazz.addImport(idToName.get(trgtId)[0] + "." + idToName.get(trgtId)[1]);
                            });
                } else if (arrowLine.equals("line") && arrowHead.equals("white_diamond")) {
                    clazzes.stream()
                            .filter(clazz -> clazz.getNameSpace().equals(idToName.get(trgtId)[0]) && clazz.getName().equals(idToName.get(trgtId)[1]))
                            .forEach(clazz -> clazz.addImport(idToName.get(srcId)[0] + "." + idToName.get(srcId)[1]));
                } else if (arrowLine.equals("dashed") && arrowHead.equals("plain")) {
                    clazzes.stream()
                            .filter(clazz -> clazz.getNameSpace().equals(idToName.get(srcId)[0]) && clazz.getName().equals(idToName.get(srcId)[1]))
                            .forEach(clazz -> clazz.addImport(idToName.get(trgtId)[0] + "." + idToName.get(trgtId)[1]));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            controller.setErrorMessage("Failed to parse File\n\n" + e.getMessage());
        }
        return clazzes;
    }

    private String addFinalIfNeeded(String string) {
        String[] sarr = string.split(" ");
        int idx = string.startsWith("static") ? 2 : 1;
        System.out.println(string);
        if (sarr[idx].equals(sarr[idx].toUpperCase())) {
            string = "final " + string;
        }
        return string;
    }

    private String removeUnnecessaryHTML(String string) {
        return string.replaceAll("<html>", "")
                .replaceAll("</html>", "")
                .replaceAll("<br>", "")
                .replaceAll("</u>", "");
    }

    private String addStaticIfNeeded(String string) {
        if (string.contains("<u>") || string.contains("</u>")) {
            string = string.replaceAll("<u>", "").replaceAll("</u>", "");
            return "static " + string;
        }
        return string;
    }
}