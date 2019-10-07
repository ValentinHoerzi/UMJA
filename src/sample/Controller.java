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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML public TextField tfProjectName;

    @FXML public TextField tfProjectPath;

    @FXML public TextField tfEnterFile;

    @FXML public Button btnConvert;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnConvert.setDisable(true);
        tfProjectPath.setPromptText(System.getProperty("user.home"));
    }

    @FXML
    public void onBtnSelectPath(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(((Node)actionEvent.getTarget()).getScene().getWindow());

        if(selectedDirectory == null){
            //No Directory selected
        }else{
            tfProjectPath.setText(selectedDirectory.getAbsolutePath());
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
            tfEnterFile.setText(file.getName());
            btnConvert.setDisable(false);
        }
    }

    @FXML
    public void onBtnConvert(ActionEvent actionEvent) {

    }


    public void onDragOverOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void onDragDropped(DragEvent event) {
        File file = event.getDragboard().getFiles().get(0);
        tfEnterFile.setText(file.getName());
    }


}
