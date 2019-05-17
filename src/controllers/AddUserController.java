package controllers;

import connectivity.ConnectionClass;
import content.contentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Encryption;
import Entities.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AddUserController extends contentController implements Initializable {
    ObservableList vyber= FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> cb;
    @FXML
    private ImageView zaregistruj;
    @FXML
    private TextField menoField;
    @FXML
     private TextField priezviskoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField hesloField;
    @FXML
    private Label zle;

    private String Data [] = new String[6];

    public void onBackBtnClick() throws IOException {

        Stage stage = (Stage) menoField.getScene().getWindow();
        stage.close();

        }
    public void getFromFields(){
        Data[3] = menoField.getText();
        Data[4] = priezviskoField.getText();
        Data[0] = usernameField.getText();
        Data[5] = emailField.getText();
        Data[1] = Encryption.MD5(hesloField.getText());
        Data[2] = cb.getValue();
        try {
            register();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void register() throws SQLException {
        Connection connection = ConnectionClass.getConnection();
        String insertQuery = "INSERT INTO pouzivatel(username,password,typ_konta,meno,priezvisko,email) VALUES(?,?,?,?,?,?)";
        System.out.println(checkFieldData());
        if (checkFieldData()) {
            try {
                PreparedStatement preparedStatementForInsert = connection.prepareStatement(insertQuery);
                for (int i = 0; i < 6; i++) {
                    preparedStatementForInsert.setString(i + 1, Data[i]);
                }
                preparedStatementForInsert.executeUpdate();
                zle.setText("Registrácia prebehla úspešne!");
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection.close();

                Stage stage = (Stage) menoField.getScene().getWindow();
                stage.close();
            }
        }
    }
    private boolean checkFieldData(){
        if (Data[0].isEmpty() ||Data[1].isEmpty() || Data[2].isEmpty() || Data[3].isEmpty() || Data[4].isEmpty() || Data[5].isEmpty()){
            zle.setText("Všetky polia sú povinné!");
            return false;
        }
        else if (!Data[5].contains("@")){
            zle.setText("Zadali ste neplatný email!");
            return false;
        }
        return true;
    }
    private void LoadData(){
        vyber.addAll(vyber);
        String a="osetrovatel";
        String b="opravar";
        vyber.addAll(a,b);
        cb.getItems().addAll(vyber);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadData();
    }
}
