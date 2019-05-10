package sample;

import connectivity.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.loginController.nameSurname;

public class RequestUsersData {

    private PreparedStatement statement = null;
    private User user;

    public User getUsersData(String login,String pass) {
        Connection connection = ConnectionClass.getConnection();
        System.out.println("CONNECTION INICIATED");
        String sql = "SELECT * FROM pouzivatel WHERE username = ? AND password = ?";

        try{
        statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, pass);
        ResultSet setOfData = statement.executeQuery();

        while (setOfData.next()){
            user = new User();
            user.setUsername(setOfData.getString("username"));
            user.setPassword(setOfData.getString("password"));
            user.setType(setOfData.getString("typ_konta"));
            user.setName(setOfData.getString("meno"));
            user.setSurname(setOfData.getString("priezvisko"));
        }
        if (user !=null) {
            nameSurname = user.getName() + " " + user.getSurname(); //opravit
        }
        return user;
    }catch (SQLException ex) {
            Logger.getLogger(RequestUsersData.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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
    }
}
