package com.thenarbox.api;

import org.bukkit.event.Listener;

import java.sql.*;
import java.util.ArrayList;

public class AllowedCommands implements Listener {


    private static ArrayList<String> allowedCommands = new ArrayList<String>();
    static final String username = "u1_T2UIK9wc4t";
    static final String password = "!Uzp!@2fuKE6na1^0Xu5gM9W";
    static final String url = "jdbc:mysql://65.108.3.94:43306/s1_AllowedCommands";

    static Connection connection;

    public static void disconnectFromMysql() {
        try {
            if (connection!=null && !connection.isClosed()){
                connection.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> initMysql(){
        try {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM `s1_AllowedCommands`.`AllowedCommands`;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                allowedCommands.add("/"+resultSet.getString("command"));
            }
            disconnectFromMysql();
            return allowedCommands;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
