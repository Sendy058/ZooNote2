package sample;

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

public class AnimalInfoController implements Initializable {
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zdravotnaKartaArea.setText(AnimalsController.selectedItem.getZdravotna_karta());
        stavField.setText(AnimalsController.selectedItem.getStav());
        stavField.setVisible(false);
        zdravotnaKartaArea.setVisible(false);

        menoLabel.setText(AnimalsController.selectedItem.getMeno());
        datumNarodeniaLabel.setText(AnimalsController.selectedItem.getDatum_narodenia());
        stavLabel.setText(AnimalsController.selectedItem.getStav());
        celadLabel.setText(AnimalsController.selectedItem.getCelad());
        radLabel.setText(AnimalsController.selectedItem.getRad());
        triedaLabel.setText(AnimalsController.selectedItem.getTrieda());
        druhLabel.setText(AnimalsController.selectedItem.getDruh());
        zdravotnaKartaLabel.setText(AnimalsController.selectedItem.getZdravotna_karta());

    }
    public void changeLabels(){
        if (loginController.curentlyLoggedUser.getType().equals("osetrovatel")) {

            stavLabel.setVisible(false);
            stavField.setVisible(true);

            zdravotnaKartaLabel.setVisible(false);
            zdravotnaKartaArea.setVisible(true);
        }
    }

    public void updateAnimal() throws SQLException {
        if (loginController.curentlyLoggedUser.getType().equals("osetrovatel")) {
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
