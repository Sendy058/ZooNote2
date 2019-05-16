package controllers;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Animal;
import sample.Messages;
import sample.User;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.sun.glass.ui.Cursor.setVisible;

public class messagesController extends loginController implements Initializable {

    private ResultSet data, resultSetSize;
    private ObservableList<Messages> adminData= FXCollections.observableArrayList();
   // private String lastSelectedName = "", selectedName = "";
  //  public static Animal selectedItem;


    @FXML
    private TableView<Messages> messagesTable;
    @FXML
    private TableColumn<User, String> nazovColumn;
    @FXML
    private TableColumn<User, String> typColumn;
    @FXML
    private TableColumn<User, String> odosielatelColumn;
    @FXML
    private TableColumn<User, String> datumColumn;


    public void mssForAdmin(){
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
            insertIntoTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setVisible(false);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void insertIntoTable() {
        try {
            Connection connection = ConnectionClass.getConnection();
            int p=resultSetSize.getInt(1);
            for (int i = 0; i < p; i++) {
                if (!data.isClosed()) {
                    adminData.add(new Messages(data.getString(2), data.getString(3), data.getString(4),data.getString(5), data.getString(6), data.getString(7), data.getString(8)));
                    data.next();

                }
            }
            nazovColumn.setCellValueFactory(new PropertyValueFactory<>("nazovSpravy"));
            typColumn.setCellValueFactory(new PropertyValueFactory<>("typSpravy"));
            odosielatelColumn.setCellValueFactory(new PropertyValueFactory<>("odosielatel"));
            datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
         //   ObservableList<Messages> adminSpravyList = FXCollections.observableArrayList(adminData);
            messagesTable.setItems(adminData);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    mssForAdmin();
    }
}
