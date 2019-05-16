package controllers;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddAnimalController {

    @FXML
    private TextArea zdravotnaKartaArea;
    @FXML
    private TextField menoField;
    @FXML
    private TextField datumField;
    @FXML
    private TextField stavField;
    @FXML
    private TextField triedaField;
    @FXML
    private TextField celadField;
    @FXML
    private TextField radField;
    @FXML
    private TextField druhField;
    @FXML
    private Label errMessage;

    private String [] data = new String [8];

    public void addAnimal() throws SQLException {

        data[0] = menoField.getText();
        data[1] = datumField.getText();
        data[2] = stavField.getText();
        data[3] = zdravotnaKartaArea.getText();
        data[4] = triedaField.getText();
        data[5] = celadField.getText();
        data[6] = radField.getText();
        data[7] = druhField.getText();

        if (data[0].isEmpty() || data[1].isEmpty() || data[2].isEmpty() || data[3].isEmpty() ||
                data[4].isEmpty() || data[5].isEmpty() || data[6].isEmpty() || data[7].isEmpty()){

            errMessage.setText("Všetky polia sú povinné!");

        }else {
            try {
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                dateFormat.parse(data[1]);

                Connection connection = ConnectionClass.getConnection();
                String insertQuery = "INSERT INTO zviera(meno,datum_narodenia,stav,zdravotna_karta,trieda,celad,rad,druh) VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatementforInsert = connection.prepareStatement(insertQuery);
                for (int i = 0; i < 8; i++) {
                    preparedStatementforInsert.setString(i + 1, data[i]);
                }
                preparedStatementforInsert.executeUpdate();
                connection.close();

                Stage stage = (Stage) menoField.getScene().getWindow();
                stage.close();
            } catch (ParseException e) {
                errMessage.setText("Zadali ste zlý dátum!");
            }
        }
    }

}
