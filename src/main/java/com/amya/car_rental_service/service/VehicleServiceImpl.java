package com.amya.car_rental_service.service;

import com.amya.car_rental_service.constants.AppConstants;
import com.amya.car_rental_service.exception.VehicleNotFoundException;
import com.amya.car_rental_service.model.Status;
import com.amya.car_rental_service.model.Vehicle;
import com.amya.car_rental_service.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle create(Vehicle vehicle) {
        vehicle.setId(UUID.randomUUID().toString());
        vehicle.setStatus(Status.AVAILABLE);
        vehicle.setOwner(null);
        vehicle.setAssociationDate(null);

        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(String id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(AppConstants.VEHICLE_NOT_FOUND + id));
    }
}