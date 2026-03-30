package com.amya.car_rental_service.model;

import com.amya.car_rental_service.constants.AppConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vehicle {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String owner;

    @JsonProperty("association_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date associationDate;

    @NotBlank(message = AppConstants.BRAND_REQUIRED)
    @Column(length = 50)
    private String brand;

    @NotBlank(message = AppConstants.MODEL_REQUIRED)
    @Column(length = 50)
    private String model;

    @NotBlank(message = AppConstants.LICENSE_PLATE_REQUIRED)
    @Column(length = 50)
    @JsonProperty("license_number")
    private String licensePlate;
}