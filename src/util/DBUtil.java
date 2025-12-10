package com.healthcare.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL =
            "jdbc:mysql://localhost:3306/healthcare_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // update if needed

    // Prevent instantiation
    private DBUtil() {}

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✔ MySQL JDBC Driver Loaded Successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver Not Found");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Database Connection Failed");
            e.printStackTrace();
            return null;
        }
    }
}
