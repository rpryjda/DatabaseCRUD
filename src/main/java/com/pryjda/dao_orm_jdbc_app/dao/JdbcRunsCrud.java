package com.pryjda.dao_orm_jdbc_app.dao;

import com.pryjda.dao_orm_jdbc_app.entities.Runs;
import com.pryjda.dao_orm_jdbc_app.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunsCrud implements DaoRunsCrud {

    private Connection connection = JdbcUtils.getInstance().getConnection();
    private Statement statement;
    private PreparedStatement preparedStatement;

    @Override
    public void create(String sqlQuery) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Runs runs) {

        String sqlQuery = "INSERT INTO runs(name, place, start_date, start_time, members_limit) VALUES (?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, runs.getName());
            preparedStatement.setString(2, runs.getPlace());
            preparedStatement.setDate(3, java.sql.Date.valueOf(runs.getStartDate()));
            preparedStatement.setTime(4, java.sql.Time.valueOf(runs.getStartTime()));
            preparedStatement.setInt(5, runs.getMembersLimit());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Runs> read() {

        String sqlQuery = "SELECT * FROM runs";
        List<Runs> results = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                Runs runs = Runs.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .place(resultSet.getString("place"))
                        .startDate(resultSet.getDate("start_date").toLocalDate())
                        .startTime(resultSet.getTime("start_time").toLocalTime())
                        .membersLimit(resultSet.getInt("members_limit"))
                        .build();
                results.add(runs);

            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public Runs read(int id) {
        String sqlQuery = "SELECT * FROM runs WHERE id = ?";
        Runs runs = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                runs = Runs.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .place(resultSet.getString("place"))
                        .startDate(resultSet.getDate("start_date").toLocalDate())
                        .startTime(resultSet.getTime("start_time").toLocalTime())
                        .membersLimit(resultSet.getInt("members_limit"))
                        .build();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return runs;
    }

    @Override
    public void update(Runs runs) {
        String sqlQuery = "UPDATE runs SET name = ?, place = ?, start_date = ?," +
                "start_time = ?, members_limit = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, runs.getName());
            preparedStatement.setString(2, runs.getPlace());
            preparedStatement.setDate(3, java.sql.Date.valueOf(runs.getStartDate()));
            preparedStatement.setTime(4, java.sql.Time.valueOf(runs.getStartTime()));
            preparedStatement.setInt(5, runs.getMembersLimit());
            preparedStatement.setInt(6, runs.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String sqlQuery = "TRUNCATE runs";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sqlQuery = "DELETE FROM runs WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
