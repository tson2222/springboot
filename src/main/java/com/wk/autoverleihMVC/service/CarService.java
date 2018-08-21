package com.wk.autoverleihMVC.service;

import com.wk.autoverleihMVC.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
@Service
public interface CarService {

    List<Car> isAvailable(List<Car> carList,LocalDate start,LocalDate end);
}