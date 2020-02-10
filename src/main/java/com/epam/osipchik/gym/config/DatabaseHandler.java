package com.epam.osipchik.gym.config;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends DataBaseConfig {
    private static DatabaseHandler instance;

    private DatabaseHandler() {
    }
    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }
    //todo connection pool connection proxy
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
       /* String connectionPath = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        return  connection = DriverManager.getConnection(connectionPath, dbUser, dbPass);*/
        Context context;
        Connection connection = null;
        try {
            context = (Context) (new InitialContext().lookup("java:comp/env"));
            DataSource ds = (DataSource) context.lookup("jdbc/gym");
             connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
