package controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Messages;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class messagesController extends loginController implements Initializable {
    private ResultSet data, resultSetSize;
    ObservableList<Messages> mssData = FXCollections.observableArrayList();
    ObservableList<Messages> mssDataUser = FXCollections.observableArrayList();
    // private String lastSelectedName = "", selectedName = "";
    //  public static Animal selectedItem;


    @FXML
    private TableView<Messages> messagesTable;
    @FXML
    private TableColumn<Messages, String> nazovColumn;
    @FXML
    private TableColumn<Messages, String> typColumn;
    @FXML
    private TableColumn<Messages, String> odosielatelColumn;
    @FXML
    private TableColumn<Messages, String> datumColumn;
    private ResultSet resultUser, user;
    private String odosielatelString;
    private int odosielatel;
    private int primatel;
    private int typKonta;


    public void dataInport() {
        Connection connection = ConnectionClass.getConnection();
        String sqlQuery = "SELECT * FROM sprava";
        String countQuery = "SELECT Count(*) FROM sprava";


        PreparedStatement preparedQuery, preparedCountStatement;
        try {
            preparedCountStatement = connection.prepareStatement(countQuery);
            resultSetSize = preparedCountStatement.executeQuery();
            resultSetSize.next();
            preparedQuery = connection.prepareStatement(sqlQuery);
            data = preparedQuery.executeQuery();
            data.next();


            insertIntoTableAdmin();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void userLoad() {
        Connection connection = ConnectionClass.getConnection();
        String sqlUser = "SELECT * FROM pouzivatel";
        String countQuery = "SELECT Count(*) FROM sprava";

        PreparedStatement preparedQuery;
        try {
            preparedQuery = connection.prepareStatement(sqlUser);
            user = preparedQuery.executeQuery();
            user.next();
            preparedQuery = connection.prepareStatement(countQuery);
            resultUser = preparedQuery.executeQuery();
            resultUser.next();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void insertIntoTableAdmin() {
        try {
            userLoad();
            Connection connection = ConnectionClass.getConnection();
            String stavString = "";
            String typ = "";

                    switch (curentlyLoggedUser.getType()) {
                        case "admin":
                    primatel=1;

                    break;
                case "osetrovatel":
                    primatel = 2;

                    break;
                case "opravar":
                    primatel = 3;

                    break;
                    }
            int p = resultSetSize.getInt(1);


            if (!data.isClosed()) {
                for (int i = 0; i < p; i++) {


    zisti(data.getInt(7));
                    System.out.println(data.getInt(6)+","+primatel);
                    System.out.println(odosielatelString+","+curentlyLoggedUser.getType());
                    if (data.getInt(6) == primatel|| odosielatelString.equals(curentlyLoggedUser.getType()) ) {

                        switch (data.getInt(2)) {
                            case 1:
                                typ = "Platba";
                                break;
                            case 2:
                                typ = "Správa";
                                break;
                        }


                        mssData.add(new Messages(typ, data.getString(3), data.getString(4), data.getString(5), data.getString(6), zisti(data.getInt(7)), data.getString(8)));
                    }
                    data.next();


                }
            }
            typColumn.setCellValueFactory(new PropertyValueFactory<>("typSpravy"));
            nazovColumn.setCellValueFactory(new PropertyValueFactory<>("nazovSpravy"));
            odosielatelColumn.setCellValueFactory(new PropertyValueFactory<>("odosielatel"));
            datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
            //   ObservableList<Messages> adminSpravyList = FXCollections.observableArrayList(mssData);
            messagesTable.setItems(mssData);
            connection.close();
            messagesTable.setItems(mssData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public String zisti(int id)throws SQLException{
        String S="";
        String sql = "SELECT typ_konta  from pouzivatel WHERE id_pouzivatel=?";
        Connection connection = ConnectionClass.getConnection();

        PreparedStatement statementForKonto = connection.prepareStatement(sql);
        statementForKonto.setInt(1,id );
        ResultSet Konto = statementForKonto.executeQuery();
        Konto.next();

        switch (Konto.getString(1)) {
            case "admin":
                S= "Admin";
                break;
            case "osetrovatel":
                S= "Ošetrovateľ";
                break;
            case "opravar":
                S= "Opravár";
                break;

        }
        odosielatelString=Konto.getString(1);
        connection.close();

        return S;
    }



    public void plusMss() throws IOException {
        Stage stage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/AddMessages.fxml"));
        stage.setTitle("Správy");
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    dataInport();

    }


    public void nastavP() throws SQLException {

        String sql = "SELECT id_pouzivatel  from pouzivatel WHERE username=?";
        Connection connection = ConnectionClass.getConnection();

        PreparedStatement statementForKonto = connection.prepareStatement(sql);
        statementForKonto.setString(1,curentlyLoggedUser.getUsername() );
        ResultSet Konto = statementForKonto.executeQuery();
        Konto.next();

        if (!Konto.isClosed() || !Konto.isClosed()) {
            typKonta=Konto.getInt(1);

            connection.close();


        }


    }
}