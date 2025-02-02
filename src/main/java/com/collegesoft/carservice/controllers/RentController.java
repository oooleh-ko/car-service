package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.model.CarRent;
import com.collegesoft.carservice.services.RentalService;
import com.collegesoft.carservice.dto.RentCarRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentController {
    private final RentalService rentalService;

    public RentController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rent")
    public CarRent rentCar(@RequestBody RentCarRequest request) {
        return rentalService.rentCar(
                request.getCarId(),
                request.getCustomerId(),
                request.getStartDate(),
                request.getEndDate()
        );
    }
}
