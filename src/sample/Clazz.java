package sample;

import java.util.List;

public class Clazz {
    private String nameSpace;
    private List<String> metohds;
    private List<String> variables;

    public Clazz(String nameSpace, List<String> metohds, List<String> variables) {
        this.nameSpace = nameSpace;
        this.metohds = metohds;
        this.variables = variables;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public List<String> getMetohds() {
        return metohds;
    }

    public void setMetohds(List<String> metohds) {
        this.metohds = metohds;
    }

    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }
}
