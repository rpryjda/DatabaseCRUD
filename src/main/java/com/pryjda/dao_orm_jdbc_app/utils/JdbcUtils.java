package com.pryjda.dao_orm_jdbc_app.utils;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {

    private static JdbcUtils ourInstance = new JdbcUtils();
    @Getter
    private Connection connection;

    private JdbcUtils() {
        String user = "user";
        String password = "pass";
        String url = "jdbc:mysql://localhost:3306/datastorage";
        String connectionOptions =
                "?serverTimezone=UTC&useSSL=false&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true";
        try {
            connection = DriverManager.getConnection(url + connectionOptions, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JdbcUtils getInstance() {
        return ourInstance;
    }

}
