package sample;

import connectivity.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccount {
    private int id;
    private double stav;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStav() {
        return stav;
    }

    public void setStav(double stav) {
        this.stav = stav;
    }

    public void nacitaj()throws SQLException{
        Connection connection = ConnectionClass.getConnection();
        String sql = "SELECT stav FROM bankovy_ucet WHERE id IS 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet setOfData = statement.executeQuery();
        setOfData.next();

        stav=setOfData.getDouble(1);
        connection.close();

    }
    public void update()throws SQLException{
        Connection connection = ConnectionClass.getConnection();
        String sql = "Update bankovy_ucet  set stav=?  WHERE id IS 1";
        PreparedStatement preparedStatementInsert = connection.prepareStatement(sql);
        preparedStatementInsert.setDouble(1,stav);
        preparedStatementInsert.executeUpdate();
        connection.close();
    }

    public void Pridaj(double x){
        try {
            nacitaj();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stav=stav+x;
        try {
            update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Uber(double x){
        try {
            nacitaj();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stav=stav-x;
        try {
            update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
