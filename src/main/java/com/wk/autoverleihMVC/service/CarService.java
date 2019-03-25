package com.wk.autoverleihMVC.service;

import com.wk.autoverleihMVC.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
@Service
public interface CarService {

    List<Booking> isAvailable(List<Booking> bookingList, LocalDate start, LocalDate end);

    boolean dateValidator(LocalDate start, LocalDate end);
}