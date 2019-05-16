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

    private String data [] = new String[6];

    public void onBackBtnClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/Admin.fxml"));
        Stage stage = (Stage) menoField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        }
    public void getFromFields(){
        data[3] = menoField.getText();
        data[4] = priezviskoField.getText();
        data[0] = usernameField.getText();
        data[5] = emailField.getText();
        data[1] = Encryption.MD5(hesloField.getText());
        data[2] = cb.getValue();
        try {
            register();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Stage stage = (Stage) menoField.getScene().getWindow();
       // stage.close();
        /*Stage stage = (Stage) menoField.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/AddUser.fxml"));
        stage.setTitle("Pridanie Pouzivatela");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/

    }
    private void register() throws SQLException {
        Connection connection = ConnectionClass.getConnection();
        String insertQuery = "INSERT INTO pouzivatel(username,password,typ_konta,meno,priezvisko,email) VALUES(?,?,?,?,?,?)";
        if (checkFieldData()) {
            try {
                PreparedStatement preparedStatementForInsert = connection.prepareStatement(insertQuery);
                for (int i = 0; i < 6; i++) {
                    preparedStatementForInsert.setString(i + 1, data[i]);
                }
                preparedStatementForInsert.executeUpdate();
                zle.setText("Registrácia prebehla úspešne!");
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection.close();
            }
        }
    }
    private boolean checkFieldData(){
        if (data[0] == null ||data[1] == null || data[2] == null || data[3] == null || data[4] == null || data[5] == null){
            zle.setText("Všetky polia sú povinné!");
            return false;
        }
        else if (!data[5].contains("@")){
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
