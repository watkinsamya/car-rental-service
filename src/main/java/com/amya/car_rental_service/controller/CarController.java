package com.amya.car_rental_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.amya.car_rental_service.model.Car;
import com.amya.car_rental_service.service.CarService;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return carService.getCars();
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }
}

