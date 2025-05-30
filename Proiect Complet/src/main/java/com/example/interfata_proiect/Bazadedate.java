package com.example.interfata_proiect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bazadedate {
    private static final String URL = "jdbc:mysql://localhost:3306/cont_bancar";
    private static final String USER = "root";
    private static final String PASSWORD = "qazwsxedc1235";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driverul MySQL nu a fost găsit.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

