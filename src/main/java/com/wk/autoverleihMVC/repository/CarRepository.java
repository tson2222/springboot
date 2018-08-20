package com.wk.autoverleihMVC.repository;

import com.wk.autoverleihMVC.model.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    @Query("select startdate,enddate from Car c")
    List<Car> findAllByDate(@Param("startdate") LocalDate date);

}

