package controllers;

import Entities.Messages;
import connectivity.ConnectionClass;

import content.contentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class messagesController extends loginController implements Initializable {
    private ResultSet data, resultSetSize;
    ObservableList<Messages> mssData = FXCollections.observableArrayList();

    // private String lastSelectedId = "", selectedId = "";
    //  public static Animal selectedItem;

    @FXML
    private ImageView deletBtn;
    @FXML
    private ImageView infoBtn;
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
    private String odosielatel = "";
    private int primatel;
    private int typKonta;
    private String lastSelectedId = "", selectedId = "";
    public static Messages selectedItem;
    private contentController c;

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
        String countQuery = "SELECT Count(*) FROM pouzivatel";

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

            switch (currentlyLoggedUser.getType()) {
                case "admin":
                    primatel = 1;

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

                    if (data.getInt(6) == primatel || odosielatelString.equals(currentlyLoggedUser.getType())) {

                        switch (data.getInt(2)) {
                            case 1:
                                typ = "Platba";
                                break;
                            case 2:
                                typ = "Správa";
                                break;
                        }


                        mssData.add(new Messages(data.getString(1), typ, data.getString(3), data.getString(4), data.getString(5), data.getString(6), zisti(data.getInt(7)), odosielatel, data.getString(8)));
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


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String zisti(int id) throws SQLException {
        String S = "";
        odosielatel = "";
        Connection connection = ConnectionClass.getConnection();
        String sql = "SELECT typ_konta,meno,priezvisko  from pouzivatel WHERE id_pouzivatel=?";
        PreparedStatement statementForKonto = connection.prepareStatement(sql);
        statementForKonto.setInt(1, id);
        ResultSet Konto = statementForKonto.executeQuery();
        Konto.next();

        switch (Konto.getString(1)) {
            case "admin":
                S = "Admin";
                break;
            case "osetrovatel":
                S = "Ošetrovateľ";
                break;
            case "opravar":
                S = "Opravár";
                break;

        }

        odosielatel = odosielatel + Konto.getString(2) + Konto.getString(3);
        odosielatelString = Konto.getString(1);

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

    public void showMessagesInfo() throws IOException {
        Stage stage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/MessagesInfo.fxml"));
        stage.setTitle("Info");
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dataInport();
        deletBtn.setVisible(false);
        infoBtn.setVisible(false);

    }

    public void showFromTable() {
        try {
            lastSelectedId = selectedId;
            selectedId = messagesTable.getSelectionModel().getSelectedItem().getId();
            selectedItem = messagesTable.getSelectionModel().getSelectedItem();

            setVisible(true);

        } catch (NullPointerException e) {
            setVisible(false);
            selectedId = "";
        }
        if (selectedId.equals(lastSelectedId)) {
            setVisible(false);

            lastSelectedId = selectedId;
            selectedId = "";
            messagesTable.getSelectionModel().clearSelection();
        }
    }

    private void setVisible(boolean bool) {
        deletBtn.setVisible(bool);
        infoBtn.setVisible(bool);


    }

    public void deleteMessages() {
        try {
            Connection connection = ConnectionClass.getConnection();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Upozornenie");
            alert.setHeaderText("Naozaj si prajete zmazať túto správu?");
            alert.setContentText(":(");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String deleteQuery = "DELETE  FROM sprava WHERE id_sprava = ?";
                PreparedStatement preparedStatementToDelete = connection.prepareStatement(deleteQuery);
                preparedStatementToDelete.setInt(1, Integer.parseInt(selectedId));
                preparedStatementToDelete.executeUpdate();
            }
            messagesTable.getItems().remove(selectedItem);
            messagesTable.refresh();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public void upDate(){
        mssData.clear();
        data=null;
        resultSetSize=null;
        dataInport();




}

}
