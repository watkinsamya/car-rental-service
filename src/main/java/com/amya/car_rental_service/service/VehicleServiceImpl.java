package com.amya.car_rental_service.service;

import com.amya.car_rental_service.constants.AppConstants;
import com.amya.car_rental_service.exception.VehicleNotFoundException;
import com.amya.car_rental_service.model.Status;
import com.amya.car_rental_service.model.Vehicle;
import com.amya.car_rental_service.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public void associate(String vehicleId, String userId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(AppConstants.VEHICLE_NOT_FOUND + vehicleId));

        if (vehicle.getStatus() == Status.ASSOCIATED) {
            throw new IllegalStateException(AppConstants.VEHICLE_ALREADY_ASSOCIATED);
        }

        vehicle.setStatus(Status.ASSOCIATED);
        vehicle.setOwner(userId);
        vehicle.setAssociationDate(new Date());

        vehicleRepository.save(vehicle);
    }

    @Override
    public void removeAssociation(String vehicleId, String userId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(AppConstants.VEHICLE_NOT_FOUND + vehicleId));

        if (vehicle.getStatus() != Status.ASSOCIATED) {
            throw new IllegalStateException(AppConstants.VEHICLE_NOT_ASSOCIATED);
        }

        if (vehicle.getOwner() == null || !vehicle.getOwner().equals(userId)) {
            throw new IllegalArgumentException(AppConstants.OWNER_DOES_NOT_MATCH);
        }

        vehicle.setOwner(null);
        vehicle.setAssociationDate(null);
        vehicle.setStatus(Status.AVAILABLE);

        vehicleRepository.save(vehicle);
    }
}