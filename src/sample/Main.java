package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        stage.setTitle("UMJA Application");
        Scene mainScene = new Scene(loader.load());
        Controller controller = loader.getController();

        stage.setScene(mainScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
