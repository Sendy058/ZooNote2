package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

class Finances extends AnchorPane {
    Finances() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LayoutOther/Finances.fxml"));

        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

