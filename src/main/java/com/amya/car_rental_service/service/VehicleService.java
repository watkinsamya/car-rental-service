package com.amya.car_rental_service.service;

import com.amya.car_rental_service.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle create(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(String id);
}