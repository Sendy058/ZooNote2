package controllers;
import connectivity.ConnectionClass;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.chart.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FinancesController  implements Initializable {

    private ResultSet opravy, resultSetSize;
    private ResultSet zamestnanci, resultSetSizeZam;
    private ResultSet zvierata, resultSetSizeZvie,resultUser,user;

    private double opravKomplet;
    private double zvieraKomplet;
    private double zamesKomplet;

    @FXML
    private Button total;
    @FXML
    private Label suma;


    private ObservableList<PieChart.Data> pieChartDataOpravy = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pieChartDataZvierata = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pieChartDataZamestnanci= FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pieChartDataTotal= FXCollections.observableArrayList();
    @FXML
    private PieChart Pchart;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
    nacitaj();
    total.setStyle("-fx-padding: 15 0 0 0;");
       // LoadData();
        nacitaj();

        // LoadData();
        try {
            opravy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            zamestnanci();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            zvierata();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        total();
        totalNacitaj();
        total.setFocusTraversable(true);



    }



    public void nacitaj() {
    }



   /* private void LoadData(){
        vyber.addAll(vyber);
        String a="celkove";
        String b="za mesiac";
        String c="za tyzden";
        vyber.addAll(a,b,c);
        obdobie.getItems().addAll(vyber);
        obdobie.getSelectionModel().selectFirst();
    }*/

    @FXML
    private void opravyNacitaj(){
        total.setStyle("-fx-padding: 10 0 0 0;");
        Pchart.setData(pieChartDataOpravy);
        Pchart.setTitle("Opravy");
        suma.setText(opravKomplet+" €");

    }

    @FXML
    private void zamestnanciNacitaj(){
        total.setStyle("-fx-padding: 10 0 0 0;");
        Pchart.setData(pieChartDataZamestnanci);
        Pchart.setTitle("Zamestnanci");
        suma.setText(zamesKomplet+" €");
    }

    @FXML
    private void zvierataNacitaj(){
        total.setStyle("-fx-padding: 10 0 0 0;");
        Pchart.setData(pieChartDataZvierata);
        Pchart.setTitle("Zvieratá");
        suma.setText(zvieraKomplet+" €");
    }

    @FXML
    private void totalNacitaj() {
        total.setStyle("-fx-padding: 15 0 0 0;");
        Pchart.setData(pieChartDataTotal);
        Pchart.setTitle("Celkovo");
        suma.setText(zvieraKomplet+opravKomplet+zamesKomplet+" €");
    }


    private void total(){
        pieChartDataTotal.addAll(
                new PieChart.Data("Opravy", opravKomplet),
                new PieChart.Data("Zamestnanci", zamesKomplet),
                new PieChart.Data("Zvieratá", zvieraKomplet));

        pieChartDataTotal.forEach(komplet ->
                komplet.nameProperty().bind(
                        Bindings.concat(
                                komplet.getName(), " ", komplet.pieValueProperty(), "€"
                        )
                ));
    }

    private void opravy() throws SQLException {
        Connection connection = ConnectionClass.getConnection();
        String sqlQuery = "SELECT * FROM opravy ";
        String countQuery = "SELECT Count(*) FROM opravy";

        PreparedStatement preparedQuery, preparedCountStatement;
        try {
            preparedCountStatement = connection.prepareStatement(countQuery);
            resultSetSize = preparedCountStatement.executeQuery();
            resultSetSize.next();
            preparedQuery = connection.prepareStatement(sqlQuery);
            opravy = preparedQuery.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int dlzka = Integer.parseInt(resultSetSize.getString(1));

        opravy.next();
        for (int i = 0; i < dlzka; i++){
            if (!opravy.isClosed()) {
                if (1 == Integer.parseInt(opravy.getString(4))) {
                    pieChartDataOpravy.add(new PieChart.Data(opravy.getString(2), opravy.getDouble(5)));
                    opravKomplet=opravKomplet+opravy.getDouble(5);



                }

                opravy.next();
            }

        }
        try {
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        pieChartDataOpravy.forEach(opravy ->
                opravy.nameProperty().bind(
                        Bindings.concat(
                                opravy.getName(), " ", opravy.pieValueProperty(), "€"
                        )
                ));

    }

    private void userLoad(){
        Connection connection = ConnectionClass.getConnection();
        String sqlUser= "SELECT * FROM pouzivatel";

        PreparedStatement preparedQuery;
        try {
            preparedQuery = connection.prepareStatement(sqlUser);
            user = preparedQuery.executeQuery();
            user.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void zvierata() throws SQLException {
        Connection connection = ConnectionClass.getConnection();
        String sqlQuery = "SELECT * FROM sprava";
        String countQuery = "SELECT Count(*) FROM sprava";
        String sqlUser= "SELECT * FROM pouzivatel";
        String countUser = "SELECT Count(*) FROM pouzivatel";

        PreparedStatement preparedQuery, preparedCountStatement;
        try {
            preparedCountStatement = connection.prepareStatement(countQuery);
            resultSetSizeZvie = preparedCountStatement.executeQuery();
            resultSetSizeZvie.next();
            preparedCountStatement = connection.prepareStatement(countUser);
            resultUser = preparedCountStatement.executeQuery();
            resultUser.next();
            preparedQuery = connection.prepareStatement(sqlQuery);
            zvierata = preparedQuery.executeQuery();
            preparedQuery = connection.prepareStatement(sqlUser);
            user = preparedQuery.executeQuery();
            user.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int dlzka = resultSetSizeZvie.getInt(1);
        System.out.println(dlzka);
        int dlzkaU=resultUser.getInt(1);
        zvierata.next();
        int p=0;


        for (int i = 0; i < dlzka; i++){
            if (!user.isClosed()){
                p=0;
                for (int j=0;j<dlzkaU;j++){
                    if(user.getString(4).equals("osetrovatel")){
                        if(user.getInt(1)==zvierata.getInt(7)){
                            p=1;
                            break;

                        }
                    }
                    user.next();
                }
            }
            user.isClosed();
            userLoad();
            if (!zvierata.isClosed()) {



                if (p==1  /*&&zvierata.getInt(5)==1 && zvierata.getInt(4)==1*/) {
                    pieChartDataZvierata.add(new PieChart.Data(zvierata.getString(3), zvierata.getDouble(5)));
                    zvieraKomplet=zvieraKomplet+zvierata.getDouble(5);
                }

                zvierata.next();

            }

        }
        try {
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        pieChartDataZvierata.forEach(zvierata ->
                zvierata.nameProperty().bind(
                        Bindings.concat(
                                zvierata.getName(), " ", zvierata.pieValueProperty(), "€"
                        )
                ));

    }



    private void zamestnanci() throws SQLException {
        int os=0;
        int op=0;
        Connection connection = ConnectionClass.getConnection();
        String sqlQuery = "SELECT * FROM pouzivatel ";
        String countQuery = "SELECT Count(*) FROM pouzivatel";

        PreparedStatement preparedQuery, preparedCountStatement;
        try {
            preparedCountStatement = connection.prepareStatement(countQuery);
            resultSetSizeZam = preparedCountStatement.executeQuery();
            resultSetSizeZam.next();
            preparedQuery = connection.prepareStatement(sqlQuery);
            zamestnanci = preparedQuery.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int dlzka = Integer.parseInt(resultSetSizeZam.getString(1));

        zamestnanci.next();
        for (int i = 0; i < dlzka; i++){
            if (!zamestnanci.isClosed()) {
                if (zamestnanci.getString(4).equals("opravar")) {
                    os++;
                }
                if (zamestnanci.getString(4).equals("osetrovatel")) {
                    op++;
                }

                zamestnanci.next();
            }

        }
        try {
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        pieChartDataZamestnanci.addAll((new PieChart.Data("Opravári",op*900)),
                (new PieChart.Data("Ošetrovatelia",os*1200)));

        pieChartDataZamestnanci.forEach(zamestnanci ->
                zamestnanci.nameProperty().bind(
                        Bindings.concat(
                                zamestnanci.getName(), " ", zamestnanci.pieValueProperty(), "€"
                        )
                ));
        zamesKomplet=op*900+os*1200;

    }



}
