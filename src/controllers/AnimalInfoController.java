package controllers;

import Entities.User;
import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.jnlp.UnavailableServiceException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AnimalInfoController extends loginController implements Initializable {
    @FXML
    private Label menoLabel;
    @FXML
    private Label datumNarodeniaLabel;
    @FXML
    private Label stavLabel;
    @FXML
    private Label triedaLabel;
    @FXML
    private Label celadLabel;
    @FXML
    private Label radLabel;
    @FXML
    private Label druhLabel;
    @FXML
    private Label zdravotnaKartaLabel;
    @FXML
    private TextField stavField;
    @FXML
    private TextArea zdravotnaKartaArea;
    private User user;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stavField.setVisible(false);
        zdravotnaKartaArea.setVisible(false);
    }

    public void setText(String zdravotnaKarta, String stav, String meno, String datumNarodenia, String celad, String rad, String trieda, String druh) {
        zdravotnaKartaArea.setText(zdravotnaKarta);
        stavField.setText(stav);

        menoLabel.setText(meno);
        datumNarodeniaLabel.setText(datumNarodenia);
        stavLabel.setText(stav);
        celadLabel.setText(celad);
        radLabel.setText(rad);
        triedaLabel.setText(trieda);
        druhLabel.setText(druh);
        zdravotnaKartaLabel.setText(zdravotnaKarta);
    }
    public void changeLabels(){
        if (currentlyLoggedUser.getType().equals("osetrovatel")) {

            stavLabel.setVisible(false);
            stavField.setVisible(true);

            zdravotnaKartaLabel.setVisible(false);
            zdravotnaKartaArea.setVisible(true);
        }
    }

    public void updateAnimal() throws SQLException {
        if (currentlyLoggedUser.getType().equals("osetrovatel")) {
            Connection connection = ConnectionClass.getConnection();
            String updateQuery = "UPDATE zviera SET stav = ?,zdravotna_karta = ? WHERE meno = ?";
            PreparedStatement preparedStatementForUpdate = connection.prepareStatement(updateQuery);
            preparedStatementForUpdate.setString(1, stavField.getText());
            preparedStatementForUpdate.setString(2, zdravotnaKartaArea.getText());
            preparedStatementForUpdate.setString(3, menoLabel.getText());
            preparedStatementForUpdate.executeUpdate();

            connection.close();
        }
        Stage stage = (Stage) menoLabel.getScene().getWindow();
        stage.close();

    }

}
