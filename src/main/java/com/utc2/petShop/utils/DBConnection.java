package com.utc2.petShop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String PROPERTIES_FILE = "dataBase/dataBase.properties";

    public static Connection getConnection(){
        Properties prop = new Properties();

        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new IOException("Không tìm thấy file: " + PROPERTIES_FILE);
            }
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        // Mỗi lần gọi đều mở kết nối mới
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
