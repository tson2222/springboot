package com.wk.autoverleihMVC.service;

import com.wk.autoverleihMVC.model.Car;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class CarServiceImpl implements CarService {

    @Override
	public List<Car> isAvailable(List<Car> carList,LocalDate start, LocalDate end) {
        List<Car> carlist1 = new ArrayList<>();
        for(Car car:carList){
            LocalDate enddate = car.getEnddate();
            LocalDate startdate = car.getStartdate();
            if (!(start.isAfter(enddate) && end.isAfter(enddate) || (start.isBefore(startdate) && end.isBefore(startdate)))) {
                System.out.println("test");
            }
            else carlist1.add(car);
        }
		return carlist1;
	}

}
