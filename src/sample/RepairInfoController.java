package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RepairInfoController implements Initializable {

    @FXML
    private Label cenaLabel, predmetLabel,popisLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void setText(Double cena, String nazov, String popis){
        this.cenaLabel.setText(cena.toString());
        this.predmetLabel.setText(nazov);
        this.popisLabel.setText(popis);
    }
}
