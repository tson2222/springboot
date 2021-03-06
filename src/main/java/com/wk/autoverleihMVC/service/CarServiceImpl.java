package com.wk.autoverleihMVC.service;

import com.wk.autoverleihMVC.model.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    //returns a list of all the cars that are not booked for the dates "start" and "end", or in between
    @Override
    public List<Booking> isAvailable(List<Booking> bookingList, LocalDate start, LocalDate end) {
        List<Booking> carlist1 = new ArrayList<>();
        for (Booking booking : bookingList) {
            LocalDate enddate = booking.getEnddate();
            LocalDate startdate = booking.getStartdate();
            if (!(start.isAfter(enddate) && end.isAfter(enddate) || (start.isBefore(startdate) && end.isBefore(startdate)))) {
            } else carlist1.add(booking);
        }
        return carlist1;
    }

    //checks if date "start" lies after date "end"
    public boolean dateValidator(LocalDate start, LocalDate end) {
        boolean b = true;
        if (start.isAfter(end)) {
            b = false;
        }
        return b;
    }

}
