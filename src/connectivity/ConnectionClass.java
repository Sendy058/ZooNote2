package connectivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

    public static Connection getConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:zoonote.db");

        } catch (Exception e) {
        }
        return null;
    }
}
