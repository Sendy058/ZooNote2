package controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Encryption;
import sample.RequestUsersData;
import sample.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class loginController implements Initializable {


    @FXML
    private TextField meno;
    @FXML
    private PasswordField heslo;
    @FXML
    private Label zabudolHeslo;
    @FXML
    private ImageView prihlas;
    @FXML
    private Label zle;



    public static String nameSurname;


    public static User curentlyLoggedUser;




    @FXML
    private void prihlasSa(){
        String login= meno.getText();
        String pass = Encryption.MD5(heslo.getText());


        RequestUsersData request = new RequestUsersData();
        curentlyLoggedUser = request.getUsersData(login, pass);

        if (curentlyLoggedUser != null) {
            if (curentlyLoggedUser.getType().equals("admin")) {
                try {
                    Stage stage = (Stage) meno.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/Admin.fxml"));
                    stage.setTitle("ZooNote");

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (curentlyLoggedUser.getType().equals("opravar")) {
                    try {
                        Stage stage = (Stage) prihlas.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/Mechanic.fxml"));
                        stage.setTitle("ZooNote");

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println(curentlyLoggedUser.getType());
                    }
                }
                if (curentlyLoggedUser.getType().equals("osetrovatel")) {
                    try {
                        Stage stage = (Stage) meno.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/Attendant.fxml"));
                        stage.setTitle("ZooNote");

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else{
                zle.setText("Zle užívateľské meno alebo heslo!");
                meno.setText("");
                heslo.setText("");
        }
    }


    @FXML
    private void ZabudolPass(){
        try {
            Stage stage = (Stage) zabudolHeslo.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/ResetPass.fxml"));
            stage.setTitle("Obnova hesla");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa načítať reset hesla");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

