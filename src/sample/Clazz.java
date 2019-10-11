package sample;

import java.util.List;

public class Clazz {
    private String nameSpace;
    private List<String> metohds;

    public Clazz(String nameSpace, List<String> metohds) {
        this.nameSpace = nameSpace;
        this.metohds = metohds;
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
}
