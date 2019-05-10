package sample;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

public class ResetPass implements Initializable {

    static String kodS ="";
    static String mail="";


    public boolean send(String mail ,String kod) {

        try {

            String host = "smtp.gmail.com";
            String user = "zoonote.ke@gmail.com";
            String pass = "Zoonote125";
            String to = mail;
            String from = "zoonote.ke@gmail.com";
            String subject = "OBNOVENIE HESLA!!";
            String text = "Overovací kód: "+kod;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.user", "username");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "25");
            props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.EnableSSL.enable", "true");

            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(text);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Chyba nepodarilo sa odoslat emial");
            return  false;

        }
    }

    public void kod(){
        String kod="";
        for (int i=0;i<6;i++){
            kod=kod+Integer.toString((int)(Math.random()*10));
        }
        kodS=kod;
    }
   /* ObservableList vyber= FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> cb;*/


   @FXML
   private ImageView load;
   @FXML
   private ImageView back;
    @FXML
    BorderPane mainPane;
    @FXML
    private TextField loginRess;

    @FXML
    private TextField kodRess;

    @FXML
    private Label ErrorResPass;

    @FXML
    private TextField hesloRes;

    @FXML
    private TextField hesloRes2;

   @FXML
   private void odosli() throws SQLException {

        String xLogin=loginRess.getText();

       String sqlEmail = "SELECT email FROM pouzivatel WHERE username = ?" ;


       Connection connection = ConnectionClass.getConnection();

       PreparedStatement statementForEmail = connection.prepareStatement(sqlEmail);
       statementForEmail.setString(1, xLogin);
       ResultSet Email = statementForEmail.executeQuery();
       Email.next();

       if (!Email.isClosed() || !Email.isClosed()) {

           mail =Email.getString(1);
           connection.close();
           kod();
            if (send(mail,kodS)==true) {
                ResetPass2 pane = new ResetPass2();
                mainPane.setCenter(pane);
                ErrorResPass.setText("Odosielanie prebehlo úspešne");

            }else ErrorResPass.setText("NEPODARLO SA ODOSLAT EMAIL");


       }
       else{
           ErrorResPass.setText("Takéto uživateľske meno neexistuje");
           loginRess.setText("");
       }
   }

   @FXML
    private void spat(){
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/sample.fxml"));
            stage.setTitle("Prihlásenie");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa nacitat Prihlasenie");
        }
    }

    @FXML
     public void over(){
       if (kodRess.getText().equals(kodS)){
           try {
               Stage stage = (Stage) back.getScene().getWindow();
               Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/ResetPass33.fxml"));
               stage.setTitle("Onovenie Hesla");

               Scene scene = new Scene(root);
               stage.setScene(scene);
               stage.show();
           } catch (IOException e) {
               e.printStackTrace();
               System.out.println("Nepodarilo sa nacitat obnovu hesla");
           }
       }
       else {
           ErrorResPass.setText("Overovací kód nie je správny");
           kodRess.setText("");
       }
     }
    @FXML
     private void nastav()throws SQLException {
        String newHeslo = hesloRes.getText();
        String newHeslo2 = hesloRes2.getText();
        if (newHeslo.length() <= 5) {
            ErrorResPass.setText("Heslo musi obsahovat minimalne 6 znakou");
        } else if (!newHeslo.equals(newHeslo2)) {
            ErrorResPass.setText("Heslo sa nezhoduju");
            hesloRes2.setText("");
        } else {

            String password ="";
            password=Encryption.MD5(newHeslo);

            String sql = "UPDATE pouzivatel SET password = ? WHERE email = ?";

            Connection connection = ConnectionClass.getConnection();

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

                // set the corresponding param
                pstmt.setString(1, password);
                pstmt.setString(2, mail);
                // update
                pstmt.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                Stage stage = (Stage) back.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LayoutOther/sample.fxml"));
                stage.setTitle("Prihlasenie");

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Nepodarilo sa nacitat prihlasenie");
            }

        }
    }
    public void initialize(URL location, ResourceBundle resources) {


    }


   /* private void LoadData(){
        vyber.addAll(vyber);
        String a="Ošetrovateľ ";
        String b="Opravar";
        vyber.addAll(a,b);
        cb.getItems().addAll(vyber);
    }
*/

}
