package com.pryjda.hibernate_app.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Runs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String place;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "members_limit")
    private int membersLimit;
}
