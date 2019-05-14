package sample;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddRepairController {
    private String[] data = new String[3];
    @FXML
    private TextField nazovField,cenaField;
    @FXML
    private TextArea popisField;
    @FXML
    private Label errMessage;
    public void addRepair() throws SQLException {

        data[0] = nazovField.getText();
        data[1] = cenaField.getText();
        data[2] = popisField.getText();


        if (data[0].isEmpty() || data[1].isEmpty() || data[2].isEmpty()){

            errMessage.setText("Vsetky polia sú povinné!");

        }else {
            Connection connection = ConnectionClass.getConnection();
            String insertQuery = "INSERT INTO opravy(nazov_opravy,popis_opravy,stav,cena) VALUES(?,?,?,?)";
            PreparedStatement preparedStatementforInsert = connection.prepareStatement(insertQuery);
            preparedStatementforInsert.setString(1,data[0]);
            preparedStatementforInsert.setString(2,data[2]);
            preparedStatementforInsert.setString(3,"0");
            preparedStatementforInsert.setString(4,data[1]);

            preparedStatementforInsert.executeUpdate();
            connection.close();

            Stage stage = (Stage) nazovField.getScene().getWindow();
            stage.close();
        }
    }
}
