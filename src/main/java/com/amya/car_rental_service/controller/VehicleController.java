package com.amya.car_rental_service.controller;

import com.amya.car_rental_service.model.Vehicle;
import com.amya.car_rental_service.service.VehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
        return service.create(vehicle);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vehicle> getAllVehicles() {
        return service.getAllVehicles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vehicle getVehicleById(@PathVariable String id) {
        return service.getVehicleById(id);
    }
}