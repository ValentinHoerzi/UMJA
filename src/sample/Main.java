package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Model m = new Model(null);
        m.parse(new File("uml.graphml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        stage.setTitle("UMJA Application");
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
