package com.pryjda.simple_jdbc_app;

import com.pryjda.simple_jdbc_app.utils.JdbcRunsCrud;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

public class Main {

    private final static String createTable = "CREATE TABLE IF NOT EXISTS runs (" +
            "id int(11) PRIMARY KEY AUTO_INCREMENT," +
            "name varchar(20)," +
            "place varchar(20)," +
            "start_date date," +
            "start_time time," +
            "members_limit int(11))";

    public static void main(String[] args) {
        //creating table:
        JdbcRunsCrud.create(createTable);

        //creating records:
        Stream.iterate(1, i -> i + 1)
                .limit(3)
                .forEach(j ->
                        JdbcRunsCrud.create("John", "New York", LocalDate.of(2018, 10, 10),
                                LocalTime.of(8, 0), 30)
                );

        //reading records:
        JdbcRunsCrud.read().forEach(System.out::println);

        //updating records:
        JdbcRunsCrud.update(2, "James", "Boston", LocalDate.of(2018, 10, 8),
                LocalTime.of(8, 30), 40);

        System.out.println();
        JdbcRunsCrud.read().forEach(System.out::println);

        //deleting records:
        JdbcRunsCrud.delete(2);

        System.out.println();
        JdbcRunsCrud.read().forEach(System.out::println);

        //clearing all table:
        JdbcRunsCrud.delete();
    }
}
