package com.pryjda.simple_jdbc_app.utils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunsCrud {

    private static Connection connection = JdbcUtils.getInstance().getConnection();
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void create(String sqlQuery) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void create(String name, String place, LocalDate startDate, LocalTime startTime, int membersLimit) {
        try {
            preparedStatement = connection
                    .prepareStatement("INSERT INTO runs(name, place, start_date, " +
                            "start_time,members_limit) VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, place);
            preparedStatement.setDate(3, java.sql.Date.valueOf(startDate));
            preparedStatement.setTime(4, java.sql.Time.valueOf(startTime));
            preparedStatement.setInt(5, membersLimit);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> read() {
        List<String> results = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * FROM runs");

            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                String rowString = row
                        .append(resultSet.getInt("id") + " ")
                        .append(resultSet.getString("name") + " ")
                        .append(resultSet.getString("place") + " ")
                        .append(resultSet.getDate("start_date") + " ")
                        .append(resultSet.getTime("start_time") + " ")
                        .append(resultSet.getInt("members_limit") + " ")
                        .toString();
                results.add(rowString);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static String read(int id) {
        String rowString = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM runs WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                rowString = row
                        .append(resultSet.getInt("id") + " ")
                        .append(resultSet.getString("name") + " ")
                        .append(resultSet.getString("place") + " ")
                        .append(resultSet.getDate("start_date") + " ")
                        .append(resultSet.getTime("start_time") + " ")
                        .append(resultSet.getInt("members_limit") + " ")
                        .toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowString;
    }

    public static void update(int id, String name, String place, LocalDate startDate, LocalTime startTime, int membersLimit) {
        try {
            preparedStatement = connection
                    .prepareStatement("UPDATE runs SET name = ?, place = ?, start_date = ?," +
                            " start_time =?, members_limit=? WHERE id = ?");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, place);
            preparedStatement.setDate(3, java.sql.Date.valueOf(startDate));
            preparedStatement.setTime(4, java.sql.Time.valueOf(startTime));
            preparedStatement.setInt(5, membersLimit);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            preparedStatement = connection
                    .prepareStatement("DELETE FROM runs WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE runs");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
