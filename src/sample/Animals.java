package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

 class Animals extends AnchorPane {
    Animals() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LayoutOther/Animals.fxml"));

        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

