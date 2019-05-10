package sample;

import bank_account.BankAccount;
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
import weather.Weather;

import java.awt.*;
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

public class HomeControler implements Initializable {
    @FXML
    private Label cas;
    @FXML
    private Label den;
@FXML
private ImageView icon;
    @FXML
    private Label teplota;

    @FXML
    private Label balance,balanceText;

    @FXML
    private Line balanceLine;

    private String money;
    private PreparedStatement statement = null;
    private BankAccount acc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(!loginController.curentlyLoggedUser.getType().equals("admin")){
            balance.setVisible(false);
            balanceText.setVisible(false);
            balanceLine.setVisible(false);
        }

        Connection connection = ConnectionClass.getConnection();
        System.out.println("CONNECTION INICIATED");
        String sql = "SELECT stav FROM bankovy_ucet WHERE id IS 1";

        try{
            statement = connection.prepareStatement(sql);
            ResultSet setOfData = statement.executeQuery();

            acc = new BankAccount();
            //acc.setId(setOfData.getInt("id"));
            acc.setStav(setOfData.getDouble("stav"));

        }catch (SQLException ex) {
            Logger.getLogger(RequestUsersData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("CONNECTION CLOSED");
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

        System.out.println(acc.getStav());
        money = String.valueOf(acc.getStav());

        balance.setText(money + "€");
        initClock();



        Weather pocasie =new Weather();
        teplota.setText(Integer.toString(pocasie.getTemp())+ " °C");
        System.out.println(pocasie.getDesc());
        Image img1 =new Image("image/pocasie1.png");
        Image img2 =new Image("image/pocasie2.png");
        Image img3 =new Image("image/pocasie3.png");
        Image img4 =new Image("image/pocasie4.png");
        Image img5 =new Image("image/pocasie5.png");
        Image img6 =new Image("image/pocasie6.png");
        Image img7 =new Image("image/pocasie7.png");
        Image img8 =new Image("image/pocasie8.png");
        Image img9 =new Image("image/pocasie9.png");
        Image img10 =new Image("image/load.gif");

        switch(pocasie.getDesc()) {
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
            default:
                icon.setImage(img10);
        }


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date yourDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(yourDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch(dayOfWeek) {
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

/*
    private void odosli() throws SQLException {
        String pattern = "dd.MM.yyyy";

        DateFormat df = new SimpleDateFormat(pattern);

        Date today = Calendar.getInstance().getTime();

        String todayString = df.format(today);


        System.out.println(todayString);

        String xLogin="ajoj";

        String sqlEmail = "SELECT meno FROM zviera WHERE datum_narodenia = ?" ;


        Connection connection = ConnectionClass.getConnection();

        PreparedStatement statementForEmail = connection.prepareStatement(sqlEmail);
        statementForEmail.setString(1, xLogin);
        ResultSet Meno = statementForEmail.executeQuery();
        Meno.next();

        if (!Meno.isClosed() || !Meno.isClosed()) {


            connection.close();
        }
    }*/

    }
