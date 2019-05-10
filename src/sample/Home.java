package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

class Home extends AnchorPane {
     Home() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LayoutOther/Home.fxml"));

        fxmlLoader.setRoot(this);



        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

