package com.wk.autoverleihMVC.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity //tells JPA to create a table in the DB named "car"
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    @Range(min = 1,max = 300)
    private Integer speed;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    public Car() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate d) {
        this.date = d;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
