package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class Main extends Application {



    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        stage.setTitle("UMJA Application");
        Model m = new Model(null);
        Parser p = new Parser(m);
        List<Clazz> clazzes = p.parse("uml_v3.graphml");
        //m.parse(new File("uml.graphml")).forEach(System.out::println);
        Scene scene = new Scene(loader.load());
        Controller controller = loader.getController();

        //Create dependence between objects
        Model model = new Model(controller);
        controller.setModel(model);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
