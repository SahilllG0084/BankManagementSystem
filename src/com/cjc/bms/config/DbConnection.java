package com.cjc.bms.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/reg36";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "mysql";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url  = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASS");

            if (url == null || url.isEmpty()) {
                url = DEFAULT_URL;
            }
            if (user == null || user.isEmpty()) {
                user = DEFAULT_USER;
            }
            if (pass == null || pass.isEmpty()) {
                pass = DEFAULT_PASS;
            }

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }
}
