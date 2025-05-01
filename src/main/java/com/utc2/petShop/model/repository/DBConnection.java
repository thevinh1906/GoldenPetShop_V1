package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    //    private static final String PROPERTIES_FILE = "dataBase/dataBase.properties"; // đúng path trong resources
//
//    public static Connection getConnection() throws SQLException {
//        Properties props = new Properties();
//
//        try (InputStream input = DBConnection.class
//                .getClassLoader()
//                .getResourceAsStream(PROPERTIES_FILE)) {
//
//            if (input == null) {
//                throw new IOException("Không tìm thấy file: " + PROPERTIES_FILE);
//            }
//
//            props.load(input);
//        } catch (IOException e) {
//            System.err.println("Lỗi đọc file cấu hình dataBase.properties:");
//            e.printStackTrace();
//        }
//
//        String url = props.getProperty("url");
//        String user = props.getProperty("username");
//        String pass = props.getProperty("password");
//
//        return DriverManager.getConnection(url, user, pass);
//    }

    private static final String PROPERTIES_FILE = "dataBase/dataBase.properties"; // Đặt file trong src/main/resources/dataBase/
    private static Connection conn;

    public static void connect() throws IOException, SQLException {
        Properties prop = new Properties();

        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new IOException("Không tìm thấy file: " + PROPERTIES_FILE);
            }
            prop.load(input);
        }

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

