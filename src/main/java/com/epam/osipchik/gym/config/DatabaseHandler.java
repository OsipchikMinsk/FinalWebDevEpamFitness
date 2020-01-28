package com.epam.osipchik.gym.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends DataBaseConfig {

    Connection dbConnection;
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionPath = "jdbc:mysql://" + dbHost+ ":"
                +dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionPath,dbUser, dbPass);
        return dbConnection;
    }
}
