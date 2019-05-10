package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

 class Users extends AnchorPane {
     Users() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LayoutOther/Users.fxml"));

        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

