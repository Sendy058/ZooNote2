package sample;

import connectivity.ConnectionClass;
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
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class usersController implements Initializable {

    @FXML
    private TextField nameUpdate;
    @FXML
    private TextField surnameUpdate;
    @FXML
    private TextField usernameUpdate;
    @FXML
    private TextField emailUpdate;
    @FXML
    private TextField typeUpdate;
    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> surnameColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> typeColumn;
    @FXML
    private ImageView deleteBtn;
    @FXML
    private ImageView updateBtn;
    @FXML
    private Label errMessage;

    private ResultSet data, resultSetSize;
    private User userArray[];
    private String lastSelectedUsername = "",selectedUsername;
    private User selectedItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection connection = ConnectionClass.getConnection();
        System.out.println("CONNECTION INICIATED");
        String sqlQuery = "SELECT * FROM pouzivatel";
        String countQuery = "SELECT Count(*) FROM pouzivatel";

        try {
            PreparedStatement preparedCountStatement = connection.prepareStatement(countQuery);
            resultSetSize = preparedCountStatement.executeQuery();
            resultSetSize.next();
            PreparedStatement preparedQuery = connection.prepareStatement(sqlQuery);
            data = preparedQuery.executeQuery();
            insertIntoTable();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                System.out.println("CONNECTION CLOSED");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        setVisible(false);
    }

    private void insertIntoTable() {
        try {
            Connection connection = ConnectionClass.getConnection();
            userArray = new User[Integer.parseInt(resultSetSize.getString(1))];
            data.next();
            for (int i = 0; i < userArray.length; i++) {
                if (!data.isClosed()) {
                    userArray[i] = new User(data.getString(5), data.getString(6), data.getString(2), data.getString(7), data.getString(4));
                    data.next();
                }
            }
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
            ObservableList<User> userObservableListList = FXCollections.observableArrayList(userArray);
            usersTable.setItems(userObservableListList);
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        }

    public void onAddUserBtnClick() throws IOException {
        Stage stage = (Stage) usersTable.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/AddUser.fxml"));
        stage.setTitle("Pridanie Pouzivatela");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showFromTable() {
        try {
            lastSelectedUsername = selectedUsername;
            selectedUsername = usersTable.getSelectionModel().getSelectedItem().getUsername();
            selectedItem = usersTable.getSelectionModel().getSelectedItem();

            setVisible(true);

        } catch (NullPointerException e) {
            setVisible(false);
            selectedUsername = "";
        }
        if (selectedUsername.equals(lastSelectedUsername)) {
            setVisible(false);

            lastSelectedUsername = selectedUsername;
            selectedUsername = "";
            usersTable.getSelectionModel().clearSelection();
        }
    }

    public void deleteUser(){
        try {
            Connection connection = ConnectionClass.getConnection();
            System.out.println("CONNECTION INICIATED");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Upozornenie");
            alert.setHeaderText("Naozaj si prajete zmazat pouzivatela "+selectedUsername+"?");
            /*alert.setContentText("Are you ok with this?");*/

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                String deleteQuery = "DELETE  FROM pouzivatel WHERE username = ?";
                PreparedStatement preparedStatementToDelete = connection.prepareStatement(deleteQuery);
                preparedStatementToDelete.setString(1,selectedUsername);
                preparedStatementToDelete.executeUpdate();
            } else {
                System.out.println("dialog closed");
            }
            usersTable.getItems().remove(selectedItem);
            connection.close();
            System.out.println("CONNECTION CLOSED");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateUser() throws SQLException {
        Connection connection = ConnectionClass.getConnection();
        System.out.println("CONNECTION INICIATED");
        if (usersTable.getSelectionModel().getSelectedItem().getName().equals(nameUpdate.getText())&&
                usersTable.getSelectionModel().getSelectedItem().getSurname().equals(surnameUpdate.getText())&&
                usersTable.getSelectionModel().getSelectedItem().getUsername().equals(usernameUpdate.getText())&&
                usersTable.getSelectionModel().getSelectedItem().getEmail().equals(emailUpdate.getText())&&
                usersTable.getSelectionModel().getSelectedItem().getType().equals(typeUpdate.getText())){
            errMessage.setText("Nic ste nezmenili!");
        }else{
            String updateQuery = "UPDATE pouzivatel SET meno = ?, priezvisko = ?, username = ?, email = ?,typ_konta = ? WHERE username IS ?";
            PreparedStatement preparedStatementToUpdate = connection.prepareStatement(updateQuery);
            preparedStatementToUpdate.setString(1,nameUpdate.getText());
            System.out.println(nameUpdate.getText());
            preparedStatementToUpdate.setString(2,surnameUpdate.getText());
            System.out.println(surnameUpdate.getText());
            preparedStatementToUpdate.setString(3,usernameUpdate.getText());
            preparedStatementToUpdate.setString(4,emailUpdate.getText());
            preparedStatementToUpdate.setString(5,typeUpdate.getText());
            preparedStatementToUpdate.setString(6,usersTable.getSelectionModel().getSelectedItem().getUsername());
            preparedStatementToUpdate.executeUpdate();

            usersTable.getSelectionModel().getSelectedItem().setName(nameUpdate.getText());
            usersTable.getSelectionModel().getSelectedItem().setSurname(surnameUpdate.getText());
            usersTable.getSelectionModel().getSelectedItem().setUsername(usernameUpdate.getText());
            usersTable.getSelectionModel().getSelectedItem().setEmail(emailUpdate.getText());
            usersTable.getSelectionModel().getSelectedItem().setType(typeUpdate.getText());
            usersTable.refresh();
            System.out.println("UPDATED SUCCESFULLY");
        }
        connection.close();
        System.out.println("CONNECTION CLOSED");
    }
    private void setVisible(boolean bool){
        deleteBtn.setVisible(bool);
        updateBtn.setVisible(bool);

        nameUpdate.setVisible(bool);
        surnameUpdate.setVisible(bool);
        usernameUpdate.setVisible(bool);
        emailUpdate.setVisible(bool);
        typeUpdate.setVisible(bool);

        errMessage.setText("");

        try {
            nameUpdate.setText(usersTable.getSelectionModel().getSelectedItem().getName());
            surnameUpdate.setText(usersTable.getSelectionModel().getSelectedItem().getSurname());
            usernameUpdate.setText(usersTable.getSelectionModel().getSelectedItem().getUsername());
            emailUpdate.setText(usersTable.getSelectionModel().getSelectedItem().getEmail());
            typeUpdate.setText(usersTable.getSelectionModel().getSelectedItem().getType());
        }catch (NullPointerException e){
        }
    }
}



