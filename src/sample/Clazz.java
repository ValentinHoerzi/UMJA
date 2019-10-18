package sample;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
    private String stereotype; // class, enumeration, interface
    private String nameSpace; //package
    private String name; // classname
    private List<String> metohds; // class methods  
    private List<String> variables; // class variables - - enum values also here
    private List<String> implementations; // class implementations (implementet interfaces)
	private List<String> imports; // imports (import java.util.ArrayList)

    public Clazz(String stereotype, String nameSpace, String name, List<String> metohds, List<String> variables, List<String> implementations, List<String> imports) {
        this.stereotype = stereotype;
        this.nameSpace = nameSpace;
        this.name = name;
        this.metohds = metohds;
        this.variables = variables;
        this.implementations = implementations;
		this.imports = imports;
    }

    public Clazz() {
    }

    public static final class Builder {
        private String stereotype;
        private String nameSpace;
        private String name;
        private List<String> metohds;
        private List<String> variables;
        private List<String> implementations;

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

        public Clazz build() {
            return new Clazz(stereotype, nameSpace, name, metohds, variables, implementations);
        }
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

    @Override
    public String toString() {
        return "Clazz{" +
                "stereotype='" + stereotype + '\'' +
                ", nameSpace='" + nameSpace + '\'' +
                ", name='" + name + '\'' +
                ", metohds=" + metohds +
                ", variables=" + variables +
                ", implementations=" + implementations +
                '}';
    }
}
