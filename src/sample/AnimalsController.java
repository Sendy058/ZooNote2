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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AnimalsController implements Initializable {
    private ResultSet data, resultSetSize;
    private Animal animalArray[];
    private String lastSelectedName = "", selectedName = "";
    public static Animal selectedItem;


    @FXML
    private TableView<Animal> animalsTable;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> dateColumn;
    @FXML
    private TableColumn<User, String> stavColumn;
    @FXML
    private TableColumn<User, String> triedaColumn;
    @FXML
    private TableColumn<User, String> celadColumn;
    @FXML
    private TableColumn<User, String> radColumn;
    @FXML
    private ImageView deleteBtn;
    @FXML
    private ImageView updateBtn;
    @FXML
    private ImageView addBtn;
    @FXML
    private ImageView zdravKartaBtn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

            Connection connection = ConnectionClass.getConnection();
            System.out.println("CONNECTION INICIATED - initialize animal");
            String sqlQuery = "SELECT * FROM zviera";
            String countQuery = "SELECT Count(*) FROM zviera";

            PreparedStatement preparedQuery, preparedCountStatement;
            try {
                preparedCountStatement = connection.prepareStatement(countQuery);
                resultSetSize = preparedCountStatement.executeQuery();
                resultSetSize.next();
                preparedQuery = connection.prepareStatement(sqlQuery);
                data = preparedQuery.executeQuery();
                insertIntoTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            setVisible(false);
            try {
                connection.close();
                System.out.println("CONNECTION CLOSED - initialize animal");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    private void insertIntoTable() {
        try {
            Connection connection = ConnectionClass.getConnection();
            System.out.println("CONNECTION INICIATED - inserting into table animal");
            animalArray = new Animal[Integer.parseInt(resultSetSize.getString(1))];
            data.next();
            for (int i = 0; i < animalArray.length; i++) {
                if (!data.isClosed()) {
                    animalArray[i] = new Animal(data.getString(2), data.getString(3), data.getString(4),data.getString(5), data.getString(6), data.getString(7), data.getString(8),data.getString(9));
                    data.next();
                }
            }
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("meno"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("datum_narodenia"));
            stavColumn.setCellValueFactory(new PropertyValueFactory<>("stav"));
            triedaColumn.setCellValueFactory(new PropertyValueFactory<>("trieda"));
            celadColumn.setCellValueFactory(new PropertyValueFactory<>("celad"));
            radColumn.setCellValueFactory(new PropertyValueFactory<>("rad"));
            ObservableList<Animal> animalObservableListList = FXCollections.observableArrayList(animalArray);
            animalsTable.setItems(animalObservableListList);
            connection.close();
            System.out.println("CONNECTION CLOSED inser into table animal");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showFromTable() {
        try {
            lastSelectedName = selectedName;
            selectedName = animalsTable.getSelectionModel().getSelectedItem().getMeno();
            selectedItem = animalsTable.getSelectionModel().getSelectedItem();

            setVisible(true);

        } catch (NullPointerException e) {
            setVisible(false);
            selectedName = "";
        }
        if (selectedName.equals(lastSelectedName)) {
            setVisible(false);

            lastSelectedName = selectedName;
            selectedName = "";
            animalsTable.getSelectionModel().clearSelection();
        }
    }

    private void setVisible(boolean bool) {
        if (loginController.curentlyLoggedUser.getType().equals("admin")) {
            deleteBtn.setVisible(false);
            addBtn.setVisible(false);
            zdravKartaBtn.setVisible(bool);

        } else if (loginController.curentlyLoggedUser.getType().equals("osetrovatel")) {
            deleteBtn.setVisible(bool);
            zdravKartaBtn.setVisible(bool);
        }
    }

    public void deleteAnimal() {
        try {
            Connection connection = ConnectionClass.getConnection();
            System.out.println("CONNECTION INICIATED - delete animal");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Upozornenie");
            alert.setHeaderText("Naozaj si prajete zmazat toto zvieratko?  - " + selectedName);
            alert.setContentText(":(");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                String deleteQuery = "DELETE  FROM zviera WHERE meno = ?";
                PreparedStatement preparedStatementToDelete = connection.prepareStatement(deleteQuery);
                preparedStatementToDelete.setString(1, selectedName);
                preparedStatementToDelete.executeUpdate();
            } else {
                System.out.println("dialog closed");
            }
            animalsTable.getItems().remove(selectedItem);
            animalsTable.refresh();
            connection.close();
            System.out.println("CONNECTION CLOSED - delete animal");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void showAnimalInfo() throws IOException {
        Stage stage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/AnimalInfo.fxml"));
        stage.setTitle("Informácie");
        stage.setScene(new Scene(root2, 400, 600));
        stage.show();
    }
    public void showAddAnimal() throws IOException {
       Stage  stage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/AddAnimal.fxml"));
        stage.setTitle("Pridanie Zvieratka");
        stage.setScene(new Scene(root2, 400, 600));
        stage.show();
    }

}
