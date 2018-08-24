package com.wk.autoverleihMVC.web;

import com.wk.autoverleihMVC.model.Booking;
import com.wk.autoverleihMVC.repository.CarRepository;
import com.wk.autoverleihMVC.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CarController {

    private final CarRepository carRepository;
    private final CarServiceImpl carService;

    @Autowired
    public CarController(CarRepository carRepository, CarServiceImpl carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }

    @GetMapping("/car/new")
    public String newCar(Model model) {
        model.addAttribute("booking", new Booking());
        return "carform";
    }

    @PostMapping("/booking")
    public String createCar(@Valid Booking booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "carform-error";
        }
        if (!(carService.dateValidator(booking.getStartdate(), booking.getEnddate())))
            return "date-error";
        booking.setStartdate(booking.getStartdate().plusDays(1));
        booking.setEnddate(booking.getEnddate().plusDays(1));
        carRepository.save(booking);
        return "redirect:/booking/" + booking.getId();
    }

    @GetMapping("/searchbydate")
    public String dateTest(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startdate,
                           @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate enddate, Model model) {
        if (!(carService.dateValidator(startdate, enddate)))
            return "redirect:/";
        List<Booking> allBookings = (List<Booking>) carRepository.findAll();
        List<Booking> availableBookings = carService.isAvailable(allBookings, startdate, enddate);
        model.addAttribute("carlist", availableBookings);
        return "searchbydate";
    }

    @GetMapping("/booking/{id}")
    public String getWidgetById(@PathVariable Long id, Model model) {
        model.addAttribute("booking", carRepository.findById(id).orElse(new Booking()));
        return "booking";
    }

    @GetMapping("/bookings")
    public String getCars(Model model) {
        model.addAttribute("carlist", carRepository.findAll());
        return "bookings";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }

    @GetMapping("/car/edit/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).orElse(new Booking()));
        return "carform";
    }

    @PostMapping("/car/{id}")
    public String updateCar(Booking booking) {
        carRepository.save(booking);
        return "redirect:/car/" + booking.getId();
    }

    @GetMapping("/booking/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "redirect:/bookings";
    }
}

