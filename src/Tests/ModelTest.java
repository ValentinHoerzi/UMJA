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
        boolean right = false;
        if(clazzes.get(0).getName().equals("Main")  && clazzes.get(0).getVariables().get(0).equals("final static - SCANNER : Scanner")
        && clazzes.get(0).getVariables().get(1).equals("final static - STORE : Store")
        && clazzes.get(0).getVariables().get(2).equals("static - user : User")
                && clazzes.get(0).getMetohds().get(0).equals("static + main(args : String[]) : void")
        ){
            right = true;
        }

        assertTrue(right);

    }

    @Test
    void parse3() {
        List<Clazz> clazzes = new ArrayList<>();
        clazzes = parser.parse(new File("user_visitor_customer.graphml"));
        System.out.println(clazzes);
        boolean right = false;



        if(clazzes.get(0).getName().equals("Customer")
                && clazzes.get(0).getVariables().get(0).equals("- name : String")
                && clazzes.get(0).getVariables().get(1).equals("- library : Game[]")
                && clazzes.get(0).getVariables().get(2).equals("- wishlist : Game[]")
                && clazzes.get(0).getMetohds().get(0).equals("+ Customer(name : String)")
                && clazzes.get(0).getMetohds().get(1).equals("+ getLibrary() : Game[]")
                && clazzes.get(0).getMetohds().get(2).equals("+ addToLibrary(game : Game) : void")
                && clazzes.get(0).getMetohds().get(3).equals("+ toString() : String")
                && clazzes.get(0).getImports().get(0).equals("net.htlgrieskirchen.pos2.plf.retrosteam.user.User")
                && clazzes.get(0).getImplementations().get(0).equals("User")
        ){
            if(clazzes.get(1).getName().equals("User")
                    && clazzes.get(1).getVariables().get(0).equals("final static + CAPACITY = 2 : int")
                    && clazzes.get(1).getMetohds().get(0).equals("+ getWishlist() : Game[]")
                    && clazzes.get(1).getMetohds().get(1).equals("+ addToWishlist(game : Game) : void")
            ){
                if(clazzes.get(2).getName().equals("Visitor")
                        && clazzes.get(2).getVariables().get(0).equals("- wishlist : Game[]")
                        && clazzes.get(2).getMetohds().get(0).equals("+ Visitor()")
                        && clazzes.get(2).getMetohds().get(1).equals("+ toString() : String")
                        && clazzes.get(2).getImports().get(0).equals("net.htlgrieskirchen.pos2.plf.retrosteam.user.User")
                        && clazzes.get(2).getImplementations().get(0).equals("User")
                ){
                    right = true;
                }
            }
        }

        assertTrue(right);
    }


    @Test
    void parseFullUml() {
        List<Clazz> clazzes = new ArrayList<>();
        clazzes = parser.parse(new File("uml.graphml"));
        System.out.println(clazzes);
        boolean right = false;

        if(clazzes.get(0).getName().equals("Main")
                && clazzes.get(0).getVariables().get(0).equals("final static - SCANNER : Scanner")
                && clazzes.get(0).getVariables().get(1).equals("final static - STORE : Store")
                && clazzes.get(0).getVariables().get(2).equals("static - user : User")
                && clazzes.get(0).getMetohds().get(0).equals("static + main(args : String[]) : void"))
        {
            if (clazzes.get(1).getName().equals("GameType")
                    && clazzes.get(1).getVariables().get(0).equals("ACTTION, ROLEPLAY, \nSTRATEGY, SPORT")
                    && clazzes.get(1).getMetohds().equals(null)){
                if (clazzes.get(2).getName().equals("Game")
                        && clazzes.get(2).getVariables().get(0).equals("- name : String")
                        && clazzes.get(2).getVariables().get(1).equals("- price : String")
                        && clazzes.get(2).getVariables().get(2).equals("- published : String")
                        && clazzes.get(2).getVariables().get(3).equals("- type : GameType")
                        && clazzes.get(2).getMetohds().get(0).equals("+ Customer(name : String)")
                        && clazzes.get(2).getMetohds().get(1).equals("+ getLibrary() : Game[]")
                        && clazzes.get(2).getMetohds().get(2).equals("+ addToLibrary(game : Game) : void")
                        && clazzes.get(2).getMetohds().get(3).equals("+ toString() : String")
                        && clazzes.get(2).getImports().get(0).equals("net.htlgrieskirchen.pos2.plf.retrosteam.user.User")
                        && clazzes.get(2).getImplementations().get(0).equals("User")){
                    if (){
                        if(clazzes.get(4).getName().equals("Customer")
                                && clazzes.get(4).getVariables().get(0).equals("- name : String")
                                && clazzes.get(4).getVariables().get(1).equals("- library : Game[]")
                                && clazzes.get(4).getVariables().get(2).equals("- wishlist : Game[]")
                                && clazzes.get(4).getMetohds().get(0).equals("+ Customer(name : String)")
                                && clazzes.get(4).getMetohds().get(1).equals("+ getLibrary() : Game[]")
                                && clazzes.get(4).getMetohds().get(2).equals("+ addToLibrary(game : Game) : void")
                                && clazzes.get(4).getMetohds().get(3).equals("+ toString() : String")
                                && clazzes.get(4).getImports().get(0).equals("net.htlgrieskirchen.pos2.plf.retrosteam.user.User")
                                && clazzes.get(4).getImplementations().get(0).equals("User")
                        ){
                            if(clazzes.get(5).getName().equals("User")
                                    && clazzes.get(5).getVariables().get(0).equals("final static + CAPACITY = 2 : int")
                                    && clazzes.get(5).getMetohds().get(0).equals("+ getWishlist() : Game[]")
                                    && clazzes.get(5).getMetohds().get(1).equals("+ addToWishlist(game : Game) : void")
                            ){
                                if(clazzes.get(6).getName().equals("Visitor")
                                        && clazzes.get(6).getVariables().get(0).equals("- wishlist : Game[]")
                                        && clazzes.get(6).getMetohds().get(0).equals("+ Visitor()")
                                        && clazzes.get(6).getMetohds().get(1).equals("+ toString() : String")
                                        && clazzes.get(6).getImports().get(0).equals("net.htlgrieskirchen.pos2.plf.retrosteam.user.User")
                                        && clazzes.get(6).getImplementations().get(0).equals("User")
                                ){
                                    right = true;
                                }
                            }
                    }
                }
            }

            }

            assertTrue(right);
        }
        }



}