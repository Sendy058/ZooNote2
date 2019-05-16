package controllers;

import sample.BankAccount;
import connectivity.ConnectionClass;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import sample.RequestUsersData;
import weather.Weather;

import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeControler extends loginController implements Initializable {

    private ResultSet date, resultSetSize;
    @FXML
    private Label cas;
    @FXML
    private Label den;
    @FXML
    private ImageView icon;
    @FXML
    private Label teplota;
    @FXML
    private Label nameDay;
    @FXML
    private Label nameDayTomorrow;
    @FXML
    private Line balanceLine;
    @FXML
    private Label balance, balanceText;

    private String money;
    private PreparedStatement statement = null;
    private BankAccount acc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!currentlyLoggedUser.getType().equals("admin")) {
            balanceLine.setVisible(false);
            balanceText.setVisible(false);
            balance.setVisible(false);
        }

        Connection connection = ConnectionClass.getConnection();

        String sql = "SELECT stav FROM bankovy_ucet WHERE id IS 1";

        try {
            statement = connection.prepareStatement(sql);
            ResultSet setOfData = statement.executeQuery();


            acc = new BankAccount();
            acc.setStav(setOfData.getDouble("stav"));

        } catch (SQLException ex) {
            Logger.getLogger(RequestUsersData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RequestUsersData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RequestUsersData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        money = String.valueOf(acc.getStav());

        balance.setText(money + "€");
        initClock();
        try {
            odosli();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Weather pocasie = new Weather();
        teplota.setText((pocasie.getTemp()) + " °C");
        Image img1 = new Image("image/pocasie1.png");
        Image img2 = new Image("image/pocasie2.png");
        Image img3 = new Image("image/pocasie3.png");
        Image img4 = new Image("image/pocasie4.png");
        Image img5 = new Image("image/pocasie5.png");
        Image img6 = new Image("image/pocasie6.png");
        Image img7 = new Image("image/pocasie7.png");
        Image img8 = new Image("image/pocasie8.png");
        Image img9 = new Image("image/pocasie9.png");
        Image img11 = new Image("image/pocasie10.png");
        Image img10 = new Image("image/load.gif");

        switch (pocasie.getDesc()) {
            case "clear sky":
                icon.setImage(img1);
                break;
            case "few clouds":
                icon.setImage(img2);
                break;
            case "scattered clouds":
                icon.setImage(img3);
                break;
            case "broken clouds":
                icon.setImage(img4);
                break;
            case "shower rain":
                icon.setImage(img5);
                break;
            case "rain":
                icon.setImage(img6);
                break;
            case "thunderstorm":
                icon.setImage(img7);
                break;
            case "snow":
                icon.setImage(img8);
                break;
            case "mist":
                icon.setImage(img9);
                break;
            case "light rain":
                icon.setImage(img11);
                break;
            default:
                icon.setImage(img10);
        }


        Date yourDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(yourDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                den.setText("nedeľa");
                break;
            case 2:
                den.setText("pondelok");
                break;
            case 3:
                den.setText("utorok");
                break;
            case 4:
                den.setText("streda");
                break;
            case 5:
                den.setText("štvrtok");
                break;
            case 6:
                den.setText("piatok");
                break;
            case 7:
                den.setText("sobota");
                break;
            default:
                den.setText("-");
        }
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            cas.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    private void odosli() throws SQLException {


        String pattern = "d.M";

        DateFormat df = new SimpleDateFormat(pattern);

        Date today = Calendar.getInstance().getTime();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();

        String todayString = df.format(today);
        String tomorrowString = df.format(dt);


        String menoToday = "";
        String menoTomorrow = "";


        Connection connection = ConnectionClass.getConnection();
        String sqlQuery = "SELECT * FROM zviera";
        String countQuery = "SELECT Count(*) FROM zviera";

        PreparedStatement preparedQuery, preparedCountStatement;
        try {
            preparedCountStatement = connection.prepareStatement(countQuery);
            resultSetSize = preparedCountStatement.executeQuery();
            resultSetSize.next();
            preparedQuery = connection.prepareStatement(sqlQuery);
            date = preparedQuery.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int dlzka = Integer.parseInt(resultSetSize.getString(1));

        date.next();
        for (int i = 0; i < dlzka; i++) {
            if (!date.isClosed()) {
                String s = "";
                int p = 0;
                for (int j = 0; j < date.getString(3).length(); j++) {
                    if (date.getString(3).charAt(j) == '.') {
                        p++;
                    }

                    if (p < 2) {
                        s = s + date.getString(3).charAt(j);
                    }

                }


                if (s.equals(todayString)) {
                    menoToday = menoToday + "," + date.getString(2);
                }
                if (s.equals(tomorrowString)) {
                    menoTomorrow = menoTomorrow + "," + date.getString(2);
                }

                date.next();

            }
        }
        try {
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (menoToday.length() == 0) menoToday = " ------";
        if (menoTomorrow.length() == 0) menoTomorrow = " ------";
        nameDay.setText(menoToday.substring(1));
        nameDayTomorrow.setText(menoTomorrow.substring(1));
    }

}