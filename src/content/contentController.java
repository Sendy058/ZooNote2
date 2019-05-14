package content;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

    @FXML
    Button hover;

    private Home home;
    private Finances finances;
    private Animals animals;
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




    /*  private BackgroundImage whiteBack = new BackgroundImage(new Image("image/Uvod_white.png",800,562,false,true),
      BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
      BackgroundSize.DEFAULT);

      private BackgroundImage normalBack = new BackgroundImage(new Image("image/uvod2.png",800,562,false,true),
              BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT);

      int i = 0;
  */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menoPriezvisko.setText(nameSurname);
        home = new Home();
        mainPane.setCenter(home);
        Butons.addAll(homeBtn,financeBtn,zvierataBtn,spraviBtn,opraviBtn,kontaBtn);
        for (Button btn:Butons) {
            if (btn==homeBtn) {
                btn.setStyle("-fx-text-fill:black;" +
                        "-fx-background-color: #80906c;");
            }
            else{
                btn.setStyle("-fx-text-fill:white;" +
                        "-fx-background-color: #80906c;"); }
        }


    }

    @FXML
    public void onHomeBtnClick() {
        Home home = new Home();
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

    /*   @FXML
        public void changeBackground() {
            if (i==1) {
                home.setBackground(new Background(whiteBack));
            }else home.setBackground(new Background(normalBack));
            i = 1-i;
            }*/
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
        animals = new Animals();
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
    }


}
