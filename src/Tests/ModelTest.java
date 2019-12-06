package Tests;

import org.junit.jupiter.api.Test;
import sample.Clazz;
import sample.Controller;
import sample.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Model model = new Model(new Controller());
    @Test
    void parse() {
        List<Clazz> clazzes = new ArrayList<>();
        clazzes = model.parse(new File("employee.graphml"));
        System.out.println(clazzes);
        assertEquals(clazzes.get(0).getName(), "Employee");
    }
}