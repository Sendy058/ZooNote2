package sample;

import connectivity.ConnectionClass;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AddUserController implements Initializable {
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
        Stage stage = (Stage) zaregistruj.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/Admin.fxml"));
        stage.setTitle("Admin");

        Scene scene = new Scene(root);
        stage.setScene(scene);
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
    }
    private void register() throws SQLException {
        Connection connection = ConnectionClass.getConnection();
        System.out.println("CONNECTION INICIATED");
        String insertQuery = "INSERT INTO pouzivatel(username,password,typ_konta,meno,priezvisko,email) VALUES(?,?,?,?,?,?)";
        if (checkFieldData()) {
            try {
                PreparedStatement preparedStatementForInsert = connection.prepareStatement(insertQuery);
                for (int i = 0; i < 6; i++) {
                    preparedStatementForInsert.setString(i + 1, data[i]);
                }
                preparedStatementForInsert.executeUpdate();
                zle.setText("Registracia prebehla uspesne!");
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection.close();
                System.out.println("CONNECTION CLOSED");
            }
        }
    }
    private boolean checkFieldData(){
        if (data[0] == null ||data[1] == null || data[2] == null || data[3] == null || data[4] == null || data[5] == null){
            zle.setText("Vsetky polia su povinne!");
            return false;
        }
        else if (!data[5].contains("@")){
            zle.setText("Zadali ste neplatny email!");
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
