package controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.BankAccount;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class AddMessages extends loginController implements Initializable {
    ObservableList vyberTyp= FXCollections.observableArrayList();
    ObservableList vyberPrijemca= FXCollections.observableArrayList();
    ObservableList vyberOperacia= FXCollections.observableArrayList();
    private int typ;
    @FXML
    private TextField nazovFile;
    @FXML
    private TextField cenaFile;
    @FXML
    private TextArea spravaFile;
    @FXML
    private Label cenaText;
    @FXML
    private Label typText;
    @FXML

    private Label changeText;
    @FXML
    private ChoiceBox prijemcaBox=new ChoiceBox();
    @FXML
    private ChoiceBox typBox=new ChoiceBox();
    @FXML
    private Label label;
    private int odosielatel;
    private int pomoc=0;
    private String [] data = new String [7];
    private BankAccount bank;

@FXML
    public void addMss() throws SQLException{
        data[1]=nazovFile.getText();
        data[3]=cenaFile.getText();
        data[2]=spravaFile.getText();

        if(typ!=2) {
            if (typBox.getValue().equals("Platba"))
                data[0] = "1";
            else
                data[0] = "2";
        }
        else
    data[0]="2";
    switch ((String)prijemcaBox.getValue()) {
        case "Admin":
            data[4] = "1";
            break;
        case"Ošetrovateľ":
            data[4] = "2";
            break;
        case "Opravár":
            data[4] = "3";
            break;
            default:
                data[4] = "1";
    }

        data[5]=Integer.toString(odosielatel);
            String pattern = "d.M.yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            Date today = Calendar.getInstance().getTime();
            String todayString = df.format(today);
            data[6]=todayString;

            if(data[0].equals("2")) {
            data[3]="0";
            cenaFile.setText("0");

    }




          if (data[3].isEmpty()||data[2].isEmpty()||data[1].isEmpty()){
            label.setVisible(true);

        }

    else {
            try {


                    Double.parseDouble(cenaFile.getText());



                if (prijemcaBox.getValue().equals("Vklad")) {

                    bank.Pridaj(Double.parseDouble(data[3]));

                }
                if (prijemcaBox.getValue().equals("Úbytok") || pomoc == 1) {
                    String nCena = "";
                    nCena = nCena + "-" + data[3];
                    data[3] = nCena;
                    bank.Pridaj(Double.parseDouble(data[3]));
                }

                for (int i = 0; i < 7; i++) {
                }
                Connection connection = ConnectionClass.getConnection();
                String insertQuery = "INSERT INTO sprava(typ_spravy,nazov_spravy,obsah,cena,prijemca,odosielatel,datum_odoslania) VALUES(?,?,?,?,?,?,?)";
                PreparedStatement preparedStatementInsert = connection.prepareStatement(insertQuery);
                for (int i = 0; i < 7; i++) {
                    if (i == 0 || i == 4 || i == 5) {
                        preparedStatementInsert.setInt(i + 1, Integer.parseInt(data[i]));
                    } else if (i == 3) {
                        if (data[3].equals("x")) {
                            data[3] = "0";
                        }
                        preparedStatementInsert.setDouble(i + 1, Double.parseDouble(data[i]));
                    } else {

                        preparedStatementInsert.setString(i + 1, data[i]);
                    }
                }
                preparedStatementInsert.executeUpdate();
                connection.close();

                Stage stage = (Stage) nazovFile.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException e) {
                label.setText("Nezadali ste správnu sumu");
                label.setVisible(true);
            }
        }


    }


    public void updateChoice(){
        if (typBox.getValue().equals("Správa")) {
            cenaText.setVisible(true);
            cenaFile.setVisible(true);
            if (currentlyLoggedUser.getType().equals("admin")) {
                prijemcaBox.getItems().removeAll(vyberPrijemca);
                prijemcaBox.getItems().addAll(vyberOperacia);
                prijemcaBox.getSelectionModel().selectFirst();
                changeText.setText("OPERÁCIA:");
            }
            if (currentlyLoggedUser.getType().equals("osetrovatel")) {
                prijemcaBox.setVisible(false);
                changeText.setVisible(false);
                pomoc=1;
            }

        }
        else {
            cenaText.setVisible(false);
            cenaFile.setVisible(false);
            if (currentlyLoggedUser.getType().equals("admin")) {
                prijemcaBox.getItems().removeAll(vyberOperacia);
                prijemcaBox.getItems().addAll(vyberPrijemca);
                prijemcaBox.getSelectionModel().selectFirst();
                changeText.setText("PRÍJEMCA:");
            }
            if (currentlyLoggedUser.getType().equals("osetrovatel")) {
                prijemcaBox.setVisible(true);
                changeText.setVisible(true);
                pomoc=0;
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bank=new BankAccount();

        vyberOperacia.addAll(vyberOperacia);
        String a3="Vklad";
        String b3="Úbytok";
        vyberOperacia.addAll(a3,b3);



            vyberTyp.addAll(vyberTyp);
            String a="Platba";
            String b="Správa";
            vyberTyp.addAll(a,b);
        typBox.getItems().addAll(vyberTyp);
        typBox.getSelectionModel().selectFirst();


            vyberPrijemca.addAll(vyberPrijemca);
            String a1="Admin";
            String b1="Opravár";
            String c1="Ošetrovateľ";

        switch (currentlyLoggedUser.getType()) {
            case "admin":{
                vyberPrijemca.addAll(b1, c1);
                prijemcaBox.getItems().addAll(vyberOperacia);
                prijemcaBox.getSelectionModel().selectFirst();
                changeText.setText("OPERÁCIA:");

            }
                break;
            case "osetrovatel": {
                vyberPrijemca.addAll(a1, b1);
                prijemcaBox.getItems().addAll(vyberPrijemca);
                prijemcaBox.getSelectionModel().selectFirst();
                prijemcaBox.setVisible(false);
                changeText.setVisible(false);
                pomoc=1;



            }
                break;
            case "opravar": {
                vyberPrijemca.addAll(a1, c1);
                prijemcaBox.getItems().addAll(vyberPrijemca);
                prijemcaBox.getSelectionModel().selectFirst();
                typBox.setVisible(false);
                cenaFile.setVisible(false);
                cenaText.setVisible(false);
                typText.setVisible(false);

                typ=2;
            }
                break;

        }

        try {
            odosielatel=zistiId();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        typBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                if (typBox.getSelectionModel().getSelectedItem() != null) {
                    updateChoice();
                }

        });


    }


    public int zistiId() throws SQLException {
        int p;
        String sql = "SELECT id_pouzivatel  from pouzivatel WHERE username=?";
        Connection connection = ConnectionClass.getConnection();

        PreparedStatement statementForKonto = connection.prepareStatement(sql);
        statementForKonto.setString(1,currentlyLoggedUser.getUsername() );
        ResultSet Konto = statementForKonto.executeQuery();
        Konto.next();

       p=Konto.getInt(1);
            connection.close();

        return p;

    }
}
