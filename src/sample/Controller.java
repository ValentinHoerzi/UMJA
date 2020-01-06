package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML public TextField txtProjectName;

    @FXML public TextField txtProjectPath;

    @FXML public TextField txtDragAndDropFile;

    @FXML public Button btnConvert;

    @FXML private Label lblErrorMessage;

    private Model model;

    private final String user_home = System.getProperty("user.home");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = Model.INSTANCE();
        btnConvert.setDisable(true);
        //Set default Path
        txtProjectPath.setPromptText(user_home);
        model.setFilePath_java(user_home);
    }

    public void onDragDropped(DragEvent event) {
        File file = event.getDragboard().getFiles().get(0);
        checkFile(file);
    }

    @FXML
    public void onBtnSelectFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.graphml"));

        File file = fileChooser.showOpenDialog(((Node)actionEvent.getTarget()).getScene().getWindow());
        checkFile(file);
    }

    private void checkFile(File file){
        if(file != null){
            model.setFilePath_graphml(file.getAbsolutePath());
            txtDragAndDropFile.setText(file.getName());
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
            if(checkForProjectName()){
                setErrorMessage("Please select a name for the project folder first");
            }else{
                setErrorMessage("");
                model.setFilePath_java(selectedDirectory.getAbsolutePath()+"\\"+txtProjectName.getText());
                txtProjectPath.setText(selectedDirectory.getAbsolutePath()+"\\"+txtProjectName.getText());
            }
        }
    }

    @FXML
    public void onBtnConvert(ActionEvent actionEvent) {
        if(checkForProjectName()){
            setErrorMessage("Please select a name for the project folder");
        }else{
            model.execute();
        }
    }

    private boolean checkForProjectName() {
        return txtProjectName.getText().equals("") || Objects.isNull(txtProjectName.getText());
    }

    public void setErrorMessage(String errorMessage){
        lblErrorMessage.setText(errorMessage);
    }

    public void onDragOverOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
}
