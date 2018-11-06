package com.pryjda.simple_jdbc_app.utils;

import lombok.Getter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {

    private static JdbcUtils ourInstance = new JdbcUtils();
    @Getter
    private Connection connection;

    private JdbcUtils() {

        String dbUser = "user";
        String dbPassword = "pass";
        String connectionUrl = "jdbc:mysql://localhost:3306/datastorage";
        String connectionOptions=
                "?serverTimezone=UTC&useSSL=false&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true";

        try {
            connection = DriverManager.getConnection(connectionUrl+connectionOptions, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JdbcUtils getInstance() {
        return ourInstance;
    }


}
