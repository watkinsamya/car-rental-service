package com.amya.car_rental_service.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amya.car_rental_service.model.Car;

@Service
public class CarService {

    private final List<Car> cars = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public Car addCar(Car car) {
        car.setId((long) (cars.size() + 1));
        cars.add(car);
        return car;
    }
}