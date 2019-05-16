package content;

import controllers.loginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private Home home;
    private Finances finances;
    private Invoices invoices;
    private Repairs repairs;
    private Users users;

    @FXML
    private Button homeBtn;
    @FXML
    private Button financeBtn;
    @FXML
    private Button zvierataBtn;
    @FXML
    private Button spraviBtn;
    @FXML
    private Button opraviBtn;
    @FXML
    private Button kontaBtn;
    private ObservableList<Button> Butons = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     menoPriezvisko.setText(currentlyLoggedUser.getName()+" "+currentlyLoggedUser.getSurname());
            home = new Home();
            mainPane.setCenter(home);
            Butons.addAll(homeBtn, financeBtn, zvierataBtn, spraviBtn, opraviBtn, kontaBtn);
            for (Button btn : Butons) {
                if (btn == homeBtn) {
                    btn.setStyle("-fx-text-fill:black;" +
                            "-fx-background-color: #80906c;");
                } else {
                    btn.setStyle("-fx-text-fill:white;" +
                            "-fx-background-color: #80906c;");

            }
        }

    }

    @FXML
    public void onHomeBtnClick() {
        home = new Home();
        mainPane.setCenter(home);

        for (Button btn:Butons) {
            if (btn==homeBtn) {
                btn.setStyle("-fx-text-fill:black;" +
                        "-fx-background-color: #80906c;"); }
            else{
                btn.setStyle("-fx-text-fill:white;" +
                        "-fx-background-color: #80906c;"); }
        }
    }

    @FXML
    public void onFinancesBtnClick() {
        finances = new Finances();
        mainPane.setCenter(finances);
        for (Button btn:Butons) {
            if (btn==financeBtn) {
                btn.setStyle("-fx-text-fill:black;" +
                        "-fx-background-color: #80906c;"); }
            else{
                btn.setStyle("-fx-text-fill:white;" +
                        "-fx-background-color: #80906c;"); }
        }
    }

    @FXML
    public void onAnimalsBtnClick() {
        Animals animals = new Animals();
        mainPane.setCenter(animals);
        for (Button btn:Butons) {
            if (btn==zvierataBtn) {
                btn.setStyle("-fx-text-fill:black;" +
                        "-fx-background-color: #80906c;"); }
            else{
                btn.setStyle("-fx-text-fill:white;" +
                        "-fx-background-color: #80906c;"); }
        }
    }

    @FXML
    public void onInvoicesBtnClick() {
        invoices = new Invoices();
        mainPane.setCenter(invoices);
        for (Button btn:Butons) {
            if (btn==spraviBtn) {
                btn.setStyle("-fx-text-fill:black;" +
                        "-fx-background-color: #80906c;"); }
            else{
                btn.setStyle("-fx-text-fill:white;" +
                        "-fx-background-color: #80906c;"); }
        }
    }

    @FXML
    public void onRepairsBtnClick() {
        repairs = new Repairs();
        mainPane.setCenter(repairs);
        for (Button btn:Butons) {
            if (btn==opraviBtn) {
                btn.setStyle("-fx-text-fill:black;" +
                        "-fx-background-color: #80906c;"); }
            else{
                btn.setStyle("-fx-text-fill:white;" +
                        "-fx-background-color: #80906c;"); }
        }
    }

    @FXML
    public void onUsersBtnClick() {
        users = new Users();
        mainPane.setCenter(users);
        for (Button btn:Butons) {
            if (btn==kontaBtn) {
                btn.setStyle("-fx-text-fill:black;" +
                        "-fx-background-color: #80906c;"); }
            else{
                btn.setStyle("-fx-text-fill:white;" +
                        "-fx-background-color: #80906c;"); }
        }
    }

    @FXML
    public void onLogoutBtnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LayoutOther/sample.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) menoPriezvisko.getScene().getWindow();
        stage.setScene(scene);
        currentlyLoggedUser.setType("");
    }



}
