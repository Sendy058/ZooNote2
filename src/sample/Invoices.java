package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

 class Invoices extends AnchorPane {
     Invoices() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LayoutOther/Invoices.fxml"));

        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

