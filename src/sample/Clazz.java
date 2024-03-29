package sample;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
    private String stereotype; // class, enum, interface
    private String nameSpace; // package
    private String name; // class name
    private List<String> metohds;
    private List<String> variables;
    private List<String> implementations;
    private List<String> imports; // import + text from list + ;

    private Clazz() {
    }

    public String getStereotype() {
        return stereotype;
    }

    public void setStereotype(String stereotype) {
        this.stereotype = stereotype;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }

    public List<String> getImplementations() {
        return implementations;
    }

    public void setImplementations(List<String> implementations) {
        this.implementations = implementations;
    }


    public void addImplementation(String implementation) {
        if (implementations == null) {
            implementations = new ArrayList<>();
        }
        implementations.add(implementation);
    }

    public void addImport(String import_string) {
        if (imports == null) {
            imports = new ArrayList<>();
        }
        if(imports.contains(import_string))return;
        imports.add(import_string);
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "stereotype='" + stereotype + '\'' +
                ", nameSpace='" + nameSpace + '\'' +
                ", name='" + name + '\'' +
                ", metohds=" + metohds +
                ", variables=" + variables +
                ", implementations=" + implementations +
                ", imports=" + imports +
                '}';
    }


    public static class Builder {
        private String stereotype;
        private String nameSpace;
        private String name;
        private List<String> metohds;
        private List<String> variables;
        private List<String> implementations;
        private List<String> imports;

        private Builder() {
        }

        public static Builder aClazz() {
            return new Builder();
        }

        public Builder withStereotype(String stereotype) {
            this.stereotype = stereotype;
            return this;
        }

        public Builder withNameSpace(String nameSpace) {
            this.nameSpace = nameSpace;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public String getName(){
            return name;
        }

        public Builder withMetohds(List<String> metohds) {
            this.metohds = metohds;
            return this;
        }

        public Builder withVariables(List<String> variables) {
            this.variables = variables;
            return this;
        }

        public Builder withImplementations(List<String> implementations) {
            this.implementations = implementations;
            return this;
        }

        public Builder withImports(List<String> imports) {
            this.imports = imports;
            return this;
        }

        public Builder addImport(String imp){
            if(imports == null) imports = new ArrayList<>();
            imports.add(imp);
            return this;
        }

        public Clazz build() {
            Clazz clazz = new Clazz();
            clazz.setStereotype(stereotype);
            clazz.setNameSpace(nameSpace);
            clazz.setName(name);
            clazz.setMetohds(metohds);
            clazz.setVariables(variables);
            clazz.setImplementations(implementations);
            clazz.setImports(imports);
            return clazz;
        }
    }
}
