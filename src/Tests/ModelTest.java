package Tests;

import org.junit.jupiter.api.Test;
import sample.Clazz;
import sample.Model;
import sample.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModelTest {

    Parser parser = new Parser(Model.INSTANCE());
    sample.Compiler compiler = new sample.Compiler();
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
                    && clazzes.get(1).getVariables().get(0).equals("ACTION, ROLEPLAY, \nSTRATEGY, SPORT")
                    ){
                if (clazzes.get(2).getName().equals("Game")
                        && clazzes.get(2).getVariables().get(0).equals("- name : String")
                        && clazzes.get(2).getVariables().get(1).equals("- price : String")
                        && clazzes.get(2).getVariables().get(2).equals("- published : String")
                        && clazzes.get(2).getVariables().get(3).equals("- type : GameType")
//                        && clazzes.get(2).getMetohds().get(0).equals("+ toString() : void)")
                        ){
                    if (clazzes.get(3).getName().equals("Store")
                            && clazzes.get(3).getVariables().get(0).equals("- games : Game[]")
                            && clazzes.get(3).getMetohds().get(0).equals("+ Store()")
                            && clazzes.get(3).getMetohds().get(1).equals("+ getGames() : Game[]")
                            && clazzes.get(3).getMetohds().get(2).equals("+ addGameToUserWishlist(user : User, gameName : String) : void")
                            && clazzes.get(3).getMetohds().get(3).equals("+ addGameToUserLibrary(user : User, gameName : String) : void")
                            && clazzes.get(3).getImports().get(0).equals("net.htlgrieskirchen.pos2.plf.retrosteam.user.User")){
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


    @Test
    void testEmployee() throws Exception {
        List<Clazz> clazzes = new ArrayList<>();

        clazzes = parser.parse(new File("employee.graphml"));
        System.out.println(clazzes);
        compiler.compile(clazzes, "./testclazzes/EmployeeTest");

        boolean t = false;


        File f = new File("./testclazzes/EmployeeTest/net/Employee.java");
        File f2 = new File("./testclazzes/EmployeeTest/net/htlgrieskirchen/pos2/plf/retrosteam/main/Employee.java");


        FileReader fR1 = new FileReader(f);
        FileReader fR2 = new FileReader(f2);

        BufferedReader reader1 = new BufferedReader(fR1);
        BufferedReader reader2 = new BufferedReader(fR2);

        String line = reader1.readLine();
        String line2 = reader2.readLine();
        while (line != null){
            if(line.equals(line2)){
                t = true;
            }else{
                System.out.println(line + ":::"+line2);
                t = false;
                break;
            }
            line = reader1.readLine();
            line2 = reader2.readLine();
        }

        //assertEquals(reader1.lines(), reader2.lines());
        assertTrue(t);
    }

    @Test
    void fullTest() throws Exception {
        List<Clazz> clazzes = new ArrayList<>();

        clazzes = parser.parse(new File("uml.graphml"));
        System.out.println(clazzes);
        compiler.compile(clazzes, "./testclazzes/FullTest");

        boolean t = false;


        File f = new File("./testclazzes/FullTest/FullUMLTest/net/htlgrieskirchen/pos2/plf/retrosteam/main/Main.java");
        File f2 = new File("./testclazzes/FullTest/net/htlgrieskirchen/pos2/plf/retrosteam/main/Main.java");

        t = compareFiles(f, f2);

        if(t){

            f = new File("./testclazzes/FullTest/FullUMLTest/net/htlgrieskirchen/pos2/plf/retrosteam/main/Customer.java");
            f2 = new File("./testclazzes/FullTest/net/htlgrieskirchen/pos2/plf/retrosteam/user/Customer.java");

            t = compareFiles(f, f2);
        }

        if(t){

            f = new File("./testclazzes/FullTest/FullUMLTest/net/htlgrieskirchen/pos2/plf/retrosteam/main/User.java");
            f2 = new File("./testclazzes/FullTest/net/htlgrieskirchen/pos2/plf/retrosteam/user/User.java");

            t = compareFiles(f, f2);
        }

        if(t){

            f = new File("./testclazzes/FullTest/FullUMLTest/net/htlgrieskirchen/pos2/plf/retrosteam/main/Visitor.java");
            f2 = new File("./testclazzes/FullTest/net/htlgrieskirchen/pos2/plf/retrosteam/user/Visitor.java");

            t = compareFiles(f, f2);
        }

        if(t){

            f = new File("./testclazzes/FullTest/FullUMLTest/net/htlgrieskirchen/pos2/plf/retrosteam/main/Game.java");
            f2 = new File("./testclazzes/FullTest/net/htlgrieskirchen/pos2/plf/retrosteam/store/Game.java");

            t = compareFiles(f, f2);
        }

        if(t){

            f = new File("./testclazzes/FullTest/FullUMLTest/net/htlgrieskirchen/pos2/plf/retrosteam/main/GameType.java");
            f2 = new File("./testclazzes/FullTest/net/htlgrieskirchen/pos2/plf/retrosteam/store/GameType.java");

            t = compareFiles(f, f2);
        }

        if(t){

            f = new File("./testclazzes/FullTest/FullUMLTest/net/htlgrieskirchen/pos2/plf/retrosteam/main/Store.java");
            f2 = new File("./testclazzes/FullTest/net/htlgrieskirchen/pos2/plf/retrosteam/store/Store.java");

            t = compareFiles(f, f2);
        }

        //assertEquals(reader1.lines(), reader2.lines());
        assertTrue(t);
    }

    private boolean compareFiles(File f, File f2){
        boolean t = false;
        try {
            FileReader fR1 = new FileReader(f);
            FileReader fR2 = new FileReader(f2);

            BufferedReader reader1 = new BufferedReader(fR1);
            BufferedReader reader2 = new BufferedReader(fR2);

            String line = reader1.readLine();
            String line2 = reader2.readLine();
            while (line != null) {
                if (line.equals(line2)) {
                    t = true;
                } else {
                    System.out.println(line + ":::" + line2);
                    return false;
                }
                line = reader1.readLine();
                line2 = reader2.readLine();
            }
            return t;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

}