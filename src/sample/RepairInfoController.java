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
        cenaLabel.setText(Double.toString(RepairsController.selectedItem.getCena()));
        predmetLabel.setText(RepairsController.selectedItem.getNazov());
        popisLabel.setText(RepairsController.selectedItem.getPopis());
    }
}
