package com.thenarbox.api;

import org.bukkit.event.Listener;

import java.sql.*;
import java.util.ArrayList;

public class AllowedCommands implements Listener {

    private static ArrayList < String > allowedCommands = new ArrayList < String > ();
    private static ArrayList < String > allowedCommands2 = new ArrayList < String > ();
    static final String username = "u1_JjGU1JmqnN";
    static final String password = "u0vSbP2@39LPmx+HNNzcf=Iu";
    static final String url = "jdbc:mysql://188.40.35.126:43306/s1_allowedcommands";

    static final String username1 = "u4_RuiJdY5u9o";
    static final String password1 = "YPjZK5h+g.EHMl2Dwu2=B!y9";
    static final String url1 = "jdbc:mysql://188.40.35.126:43306/s4_AllowedCommandsSurvival";

    static Connection connection;

    public static void disconnectFromMysql() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList < String > initSurvivalMysql(){
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url1, username1, password1);
            String sql = "SELECT * FROM `s4_AllowedCommandsSurvival`.`AllowedCommands`;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                allowedCommands2.add(resultSet.getString("command"));
            }
            disconnectFromMysql();
            return allowedCommands2;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            }
            catch (Throwable ex){
            }
        }
        return null;
    }

    public static ArrayList < String > initMysql() {

        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM `s1_AllowedCommands`.`AllowedCommands`;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                allowedCommands.add(resultSet.getString("command"));
            }
            disconnectFromMysql();
            return allowedCommands;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            }
            catch (Throwable ex){
            }
        }
        return null;
    }
}
