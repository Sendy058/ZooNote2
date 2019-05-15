package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class contentController extends loginController implements Initializable {

    @FXML
    BorderPane mainPane;
    @FXML
    Label menoPriezvisko;

    @FXML
    Button hover;

    private Home home;
    private Finances finances;
    private Animals animals;
    private Invoices invoices;
    private Repairs repairs;
    private Users users;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home = new Home();
        mainPane.setCenter(home);
    }

    @FXML
    public void onHomeBtnClick() {
        Home home = new Home();
        mainPane.setCenter(home);
    }

    @FXML
    public void onFinancesBtnClick(){
        finances = new Finances();
        mainPane.setCenter(finances);
    }

    @FXML
    public void onAnimalsBtnClick(){
        animals = new Animals();
        mainPane.setCenter(animals);
    }

    @FXML
    public void onInvoicesBtnClick(){
        invoices = new Invoices();
        mainPane.setCenter(invoices);
    }

    @FXML
    public void onRepairsBtnClick(){
        repairs = new Repairs();
        mainPane.setCenter(repairs);
    }

    @FXML
    public void onUsersBtnClick(){
        users = new Users();
        mainPane.setCenter(users);
    }

    @FXML
    public void onLogoutBtnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LayoutOther/sample.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) menoPriezvisko.getScene().getWindow();
        stage.setScene(scene);
    }

    public void setNameSurname(String name, String surname){
        menoPriezvisko.setText(name+" "+surname);
    }

}
