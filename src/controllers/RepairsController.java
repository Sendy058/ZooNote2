package controllers;


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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Repair;
import sample.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RepairsController implements Initializable {

    @FXML
    private ChoiceBox filterBox = new ChoiceBox();
    @FXML
    private ImageView showInfoBtn, approveBtn, dismissBtn, addRepairBtn;
    @FXML
    private TableView<Repair> repairsTable;
    @FXML
    private TableColumn<Repair, String> nazovColumn;
    @FXML
    private TableColumn<Repair, Double> cenaColumn;
    @FXML
    private TableColumn<Repair, String> stavColumn;
    @FXML
    private Label workaroundLabel;

    private ResultSet dataRepairs;
    private String lastSelectedName = "", selectedName = "";
    private Repair selectedItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filterBox.setItems(FXCollections.observableArrayList("Prebiehajúce", "Potvrdené", "Zamietnuté", new Separator(), "Všetky"));
        filterBox.getSelectionModel().selectLast();

        workaroundLabel.setVisible(false);


        try {
            insertIntoTable("4");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        filterBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (filterBox.getSelectionModel().getSelectedItem() != null) {
                    System.out.println(newValue);
                    System.out.println(newValue.toString() + " -index");
                    insertIntoTable(newValue.toString());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        if (workaroundLabel.getText().equals("opravar")) {
            addRepairBtn.setVisible(true);
        } else addRepairBtn.setVisible(false);

        approveBtn.setVisible(false);
        dismissBtn.setVisible(false);
        showInfoBtn.setVisible(false);

        dismissBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setRepairStav("2"));
        approveBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setRepairStav("1"));

    }

    private void insertIntoTable(String filter) throws SQLException {
        Connection connection = ConnectionClass.getConnection();
        PreparedStatement selectRepairs, selectCount;
        String selectCountSQL = "SELECT Count(*) FROM opravy";
        assert connection != null;
        selectCount = connection.prepareStatement(selectCountSQL);
        ResultSet arrLen = selectCount.executeQuery();
        arrLen.next();

        ObservableList<Repair> filteredList = FXCollections.observableArrayList();
        ObservableList<Repair> repairObservableList = FXCollections.observableArrayList();

        if (filter == null) {
            filter = "4";
        }

        String selectAllSQL = "SELECT * FROM opravy ORDER BY stav";
        selectRepairs = connection.prepareStatement(selectAllSQL);

        dataRepairs = selectRepairs.executeQuery();
        dataRepairs.next();

        String stavString = "";
        for (int i = 0; i < Integer.parseInt(arrLen.getString(1)); i++) {
            switch (dataRepairs.getInt(4)) {
                case 0:
                    stavString = "Prebiehajúce";
                    break;
                case 1:
                    stavString = "Potvrdené";
                    break;
                case 2:
                    stavString = "Zamietnuté";
                    break;
            }
            repairObservableList.add(new Repair(dataRepairs.getString(2), dataRepairs.getString(3), stavString, dataRepairs.getDouble(5)));

            if (dataRepairs.getString(4).equals(filter) && !dataRepairs.getString(4).isEmpty()) {
                filteredList.add(new Repair(dataRepairs.getString(2), dataRepairs.getString(3), stavString, dataRepairs.getDouble(5)));
            }
            dataRepairs.next();
        }
        nazovColumn.setCellValueFactory(new PropertyValueFactory<>("nazov"));
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        stavColumn.setCellValueFactory(new PropertyValueFactory<>("stav"));

        if (filter.equals("4")) {
            repairsTable.setItems(repairObservableList);
        } else {
            if (filteredList.isEmpty()) {
                filteredList.add(null);
            }
            repairsTable.setItems(filteredList);
        }
        repairsTable.refresh();
        connection.close();
    }

    public void showFromTable() {
        try {
            lastSelectedName = selectedName;
            selectedName = repairsTable.getSelectionModel().getSelectedItem().getNazov();
            selectedItem = repairsTable.getSelectionModel().getSelectedItem();

            setVisible(true);

        } catch (NullPointerException e) {
            setVisible(false);
            selectedName = "";
        }
        if (selectedName.equals(lastSelectedName)) {
            setVisible(false);

            lastSelectedName = selectedName;
            selectedName = "";
            repairsTable.getSelectionModel().clearSelection();
        }
    }

    private void setVisible(boolean bool) {

        if (workaroundLabel.getText().equals("admin")) {
            dismissBtn.setVisible(bool);
            approveBtn.setVisible(bool);
            showInfoBtn.setVisible(bool);

        } else if (workaroundLabel.getText().equals("opravar")) {
            dismissBtn.setVisible(false);
            approveBtn.setVisible(false);
            showInfoBtn.setVisible(bool);
        }
    }

    public void showAddRepair() throws IOException {
        Stage stage = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/AddRepair.fxml"));
        stage.setTitle("Pridanie Opravy");
        stage.setScene(new Scene(root2, 400, 600));
        stage.show();
    }

    public void showRepairInfo() throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getClassLoader().getResource("LayoutOther/RepairInfo.fxml"));
        Parent root2 = Loader.load();

        RepairInfoController info = Loader.getController();
        info.setText(selectedItem.getCena(), selectedItem.getNazov(), selectedItem.getPopis());

        Stage stage = new Stage();
        stage.setTitle("Informácie o oprave");
        stage.setScene(new Scene(root2, 400, 600));
        stage.show();
    }

    private void setRepairStav(String stav) {
        Connection connection = ConnectionClass.getConnection();
        String updateSql = "UPDATE opravy SET stav = ? WHERE nazov_opravy LIKE ?";
        try {
            PreparedStatement preparedStatementForUpdate = connection.prepareStatement(updateSql);
            preparedStatementForUpdate.setString(1, stav);
            preparedStatementForUpdate.setString(2, selectedName);
            preparedStatementForUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        switch (stav) {
            case "1":
                stav = "Potvrdené";
                break;
            case "2":
                stav = "Zamietnuté";
                break;
        }
        repairsTable.getSelectionModel().getSelectedItem().setStav(stav);
        repairsTable.refresh();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void getCurrentlyLoggedUser(User u) {
        workaroundLabel.setText(u.getType());
        System.out.println(workaroundLabel.getText()+"labeltext");
    }
}