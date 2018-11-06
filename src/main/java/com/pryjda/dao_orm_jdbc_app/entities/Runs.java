package com.pryjda.dao_orm_jdbc_app.entities;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
public class Runs {

    private int id;
    private String name;
    private String place;
    private LocalDate startDate;
    private LocalTime startTime;
    private int membersLimit;

}
