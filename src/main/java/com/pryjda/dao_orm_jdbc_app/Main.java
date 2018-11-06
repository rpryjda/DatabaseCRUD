package com.pryjda.dao_orm_jdbc_app;

import com.pryjda.dao_orm_jdbc_app.dao.DaoRunsCrud;
import com.pryjda.dao_orm_jdbc_app.dao_provider.DaoProvider;
import com.pryjda.dao_orm_jdbc_app.entities.Runs;

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

        DaoRunsCrud runsInstance = DaoProvider.getInstance().getRunsInstance();

        runsInstance.create(createTable);

        //creating records:
        Stream.iterate(1, i -> i + 1)
                .limit(5)
                .forEach(j -> {
                    Runs runs = Runs.builder()
                            .name("John")
                            .place("Moscow")
                            .startDate(LocalDate.of(1990, 12, 12))
                            .startTime(LocalTime.of(10, 0))
                            .membersLimit(50)
                            .build();

                    runsInstance.create(runs);
                });

        //reading records:
        runsInstance.read().forEach(System.out::println);

        //updating records:
        Stream.iterate(3, i -> i + 1)
                .limit(3)
                .forEach(j -> {
                    Runs runs = Runs.builder()
                            .id(j)
                            .name("Mike")
                            .place("Warsaw")
                            .startDate(LocalDate.of(1995, 10, 10))
                            .startTime(LocalTime.of(8, 0))
                            .membersLimit(70)
                            .build();
                    runsInstance.update(runs);
                });

        System.out.println();
        runsInstance.read().forEach(System.out::println);

        //deleting records:
        runsInstance.delete(1);
        runsInstance.delete(4);

        System.out.println();
        runsInstance.read().forEach(System.out::println);

        runsInstance.delete();

    }
}
