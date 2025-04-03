package com.utc2.petShop.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection conn;

    public static void connect() throws IOException, SQLException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("database.properties");

        prop.load(input);

        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully.");
        }
    }

    public static Connection getConnection() throws IOException, SQLException {
        if (conn == null || conn.isClosed()) {
            connect();
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
