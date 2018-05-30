package com.manager.connector;

import main.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcConnector {

    protected Connection dbConnection = null;
    protected PreparedStatement preparedStatement = null;

    protected void initiateConnection() {

        dbConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            dbConnection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void closeDBConnection() {
        try{
            if (dbConnection != null) {
                dbConnection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
