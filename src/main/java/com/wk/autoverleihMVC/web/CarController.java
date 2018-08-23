package com.wk.autoverleihMVC.web;

import com.wk.autoverleihMVC.model.Car;
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
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/car")
    public String createCar(@Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "carform-error";
        }
        if (!(carService.dateValidator(car.getStartdate(), car.getEnddate())))
            return "date-error";
        car.setStartdate(car.getStartdate().plusDays(1));
        car.setEnddate(car.getEnddate().plusDays(1));
        carRepository.save(car);
        return "redirect:/car/" + car.getId();
    }

    @GetMapping("/searchbydate")
    public String dateTest(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startdate,
                           @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate enddate, Model model) {
        if (!(carService.dateValidator(startdate, enddate)))
            return "redirect:/";
        List<Car> allCars = (List<Car>) carRepository.findAll();
        List<Car> availableCars = carService.isAvailable(allCars, startdate, enddate);
        model.addAttribute("carlist", availableCars);
        return "searchbydate";
    }

    @GetMapping("/car/{id}")
    public String getWidgetById(@PathVariable Long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).orElse(new Car()));
        return "car";
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("carlist", carRepository.findAll());
        return "cars";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }

    @GetMapping("/car/edit/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).orElse(new Car()));
        return "carform";
    }

    @PostMapping("/car/{id}")
    public String updateCar(Car car) {
        carRepository.save(car);
        return "redirect:/car/" + car.getId();
    }

    @GetMapping("/car/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "redirect:/cars";
    }
}

