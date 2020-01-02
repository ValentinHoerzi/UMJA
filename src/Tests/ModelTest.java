package Tests;

import org.junit.jupiter.api.Test;
import sample.Clazz;
import sample.Controller;
import sample.Model;
import sample.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Model model = new Model(new Controller());
    Parser parser = new Parser(model);
    @Test
    void parse() {
        List<Clazz> clazzes = new ArrayList<>();
        clazzes = parser.parse(new File("employee.graphml"));
        System.out.println(clazzes);
        assertEquals(clazzes.get(0).getName(), "Employee");
    }

    @Test
    void parse2() {
        List<Clazz> clazzes = new ArrayList<>();
        clazzes = parser.parse(new File("main.graphml"));
        System.out.println(clazzes);
        if(clazzes.get(0).getName() == "Main" && clazzes.get(0).getVariables().get(0) == "final static - SCANNER : Scanner"
        && clazzes.get(0).getVariables().get(1) == "final static - STORE : Store"
        && clazzes.get(0).getVariables().get(2) == "static - user : User"
                && clazzes.get(0).getMetohds().get(0) == "static + main(args : String[]) : void"
        ){
            assertTrue(true);
        }

    }

    @Test
    void parse3() {
        List<Clazz> clazzes = new ArrayList<>();
        clazzes = parser.parse(new File("user_visitor_customer.graphml"));
        System.out.println(clazzes);
        if(clazzes.get(0).getName() == "Customer" && clazzes.get(0).getVariables().get(0) == "final static - SCANNER : Scanner"
                && clazzes.get(0).getVariables().get(1) == "final static - STORE : Store"
                && clazzes.get(0).getVariables().get(2) == "static - user : User"
                && clazzes.get(0).getMetohds().get(0) == "static + main(args : String[]) : void"
        ){
            assertTrue(true);
        }

    }
}