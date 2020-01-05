package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML public TextField tfProjectName;

    @FXML public TextField tfProjectPath;

    @FXML public TextField tfEnterFile;

    @FXML public Button btnConvert;

    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = Model.INSTANCE();
        btnConvert.setDisable(true);
        tfProjectPath.setPromptText(System.getProperty("user.home"));
    }

    public void onDragDropped(DragEvent event) {
        File file = event.getDragboard().getFiles().get(0);

        if(file != null){
            model.setFilePath_graphml(file.getAbsolutePath());
            tfEnterFile.setText(file.getName());
            btnConvert.setDisable(false);
        }
    }

    @FXML
    public void onBtnSelectFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.graphml"));

        File file = fileChooser.showOpenDialog(((Node)actionEvent.getTarget()).getScene().getWindow());

        if(file != null){
            model.setFilePath_graphml(file.getAbsolutePath());
            tfEnterFile.setText(file.getName());
            btnConvert.setDisable(false);
        }
    }

    @FXML
    public void onBtnSelectPath(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(((Node)actionEvent.getTarget()).getScene().getWindow());

        if(selectedDirectory == null){
            //No Directory selected
        }else{
            model.setFilePath_java(selectedDirectory.getAbsolutePath());
            tfProjectPath.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    public void onBtnConvert(ActionEvent actionEvent) {
        model.execute();
    }

    public void onDragOverOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void setErrorMessage(String errorMessage){
        System.err.println(errorMessage);
    }
}
