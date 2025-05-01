package com.utc2.petShop.model.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final String PROPERTIES_FILE = "dataBase/dataBase.properties"; // đúng path trong resources

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();

        try (InputStream input = DBConnection.class
                .getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE)) {

            if (input == null) {
                throw new IOException("Không tìm thấy file: " + PROPERTIES_FILE);
            }

            props.load(input);
        } catch (IOException e) {
            System.err.println("Lỗi đọc file cấu hình dataBase.properties:");
            e.printStackTrace();
        }

        String url = props.getProperty("url");
        String user = props.getProperty("username");
        String pass = props.getProperty("password");

        return DriverManager.getConnection(url, user, pass);
    }
}
