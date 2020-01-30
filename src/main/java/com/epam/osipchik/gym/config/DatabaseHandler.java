package com.epam.osipchik.gym.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends DataBaseConfig {
    private static DatabaseHandler instance;
    Connection dbConnection;

    private DatabaseHandler() {

    }

    public static DatabaseHandler getInstance() {
        if (instance == null)
            instance = new DatabaseHandler();
        return instance;
    }



    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionPath = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionPath, dbUser, dbPass);
        return dbConnection;
    }
}
