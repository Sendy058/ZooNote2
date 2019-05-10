package sample;

import connectivity.ConnectionClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import weather.Weather;


import java.sql.Connection;

public class Main extends Application {

    private static Connection connection;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../LayoutOther/sample.fxml"));
        primaryStage.setTitle("PRIHLÁSENIE");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("image/panda.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {

        connection = ConnectionClass.getConnection();
        if(connection == null) {
            System.exit(1);
        }
        launch(args);


    }


}