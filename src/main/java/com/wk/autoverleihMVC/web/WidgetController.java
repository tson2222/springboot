package com.wk.autoverleihMVC.web;

import com.wk.autoverleihMVC.model.Car;
import com.wk.autoverleihMVC.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class WidgetController {

    private final CarRepository carRepository;

    @Autowired
    public WidgetController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/car/new")
    public String newCar(Model model) {
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/car")
    public String createCar(Car car) {
        carRepository.save(car);
        return "redirect:/car/" + car.getId();
    }

    @GetMapping("/searchbydate")
    public String dateTest(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date, Model model) {

        List<Car> carList = carRepository.findAllByDate(date.plusDays(1));
        model.addAttribute("carlist2", carList);
        return "searchbydate";
    }

    @RequestMapping("/youtube")
    public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException {
        URI yt = new URI("https://www.youtube.com/watch?v=mt1YWbSTjx8");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(yt);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
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

    @GetMapping("/car/edit/{id}")
    public String editWidget(@PathVariable Long id, Model model) {
        model.addAttribute("car", carRepository.findById(id));
        return "carform";
    }

    @PostMapping("/car/{id}")
    public String updateWidget(Car car) {
        carRepository.save(car);
        return "redirect:/car/" + car.getId();
    }

    @GetMapping("/car/delete/{id}")
    public String deleteWidget(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "redirect:/cars";
    }
}

