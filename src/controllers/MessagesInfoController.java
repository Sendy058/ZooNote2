package controllers;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MessagesInfoController extends loginController implements Initializable {
    @FXML
    private Label predmetSet;
    @FXML
    private Label typSpraviSet;
    @FXML
    private Label odosielatelSet;
    @FXML
    private Label datumSet;
    @FXML
    private Label sumaSet;
    @FXML
    private Label obsahSet;
    @FXML
    private Label suma;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        predmetSet.setText(messagesController.selectedItem.getNazovSpravy());

        switch (messagesController.selectedItem.getTypSpravy()) {
            case "Platba":
                sumaSet.setVisible(true);
                suma.setVisible(true);
                break;
            case"Správa":
                sumaSet.setVisible(false);
                suma.setVisible(false);
                break;

        }
        typSpraviSet.setText(messagesController.selectedItem.getTypSpravy());
        odosielatelSet.setText(messagesController.selectedItem.getOdosielatelName());
        datumSet.setText(messagesController.selectedItem.getDatum());
        sumaSet.setText(messagesController.selectedItem.getCena());
        obsahSet.setText(messagesController.selectedItem.getObsah());


    }

    public void konec(){
        Stage stage = (Stage) suma.getScene().getWindow();
        stage.close();
    }

}
