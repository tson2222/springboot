package com.wk.autoverleihMVC.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @Size(min = 3, max = 40)
    private String name;
    @Min(100)
    @Max(300)
    @NotNull
    private Integer speed;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate startdate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate enddate;

    public Booking() {
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate d) {
        this.startdate = d;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
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
