package com.pryjda.hibernate_app;

import com.pryjda.hibernate_app.dao.DaoRunsCrud;
import com.pryjda.hibernate_app.dao_provider.DaoProvider;
import com.pryjda.hibernate_app.entities.Runs;
import com.pryjda.hibernate_app.utils.HibernateUtils;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        DaoRunsCrud runsInstance = DaoProvider.getInstance().getRunsInstance();

        //creating records:
        for (int i = 1; i <= 5; i++) {
            Runs runs = new Runs();
            runs.setName("Bob");
            runs.setPlace("Cracow");
            runs.setStartDate(LocalDate.of(2000, 12, 12));
            runs.setStartTime(LocalTime.of(8, 0));
            runs.setMembersLimit(90);
            runsInstance.create(runs);
        }

        //reading records:
        runsInstance.read().forEach(System.out::println);
        System.out.println();
        System.out.println(runsInstance.read(2));

        //updating records:
        Runs runs = new Runs();
        runs.setId(2);
        runs.setName("Mike");
        runs.setPlace("Warsaw");
        runs.setStartDate(LocalDate.of(2010, 10, 10));
        runs.setStartTime(LocalTime.of(8, 0));
        runs.setMembersLimit(150);
        runsInstance.update(runs);

        System.out.println();
        runsInstance.read().forEach(System.out::println);

        //deleting records:
        runsInstance.delete(4);
        System.out.println();
        runsInstance.read().forEach(System.out::println);

        runsInstance.delete(runs);
        System.out.println();
        runsInstance.read().forEach(System.out::println);

        runsInstance.delete();

        HibernateUtils.getInstance().getSessionFactory().close();
    }
}
