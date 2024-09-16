package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.model.CarRent;
import com.collegesoft.carservice.services.RentalService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/rentals")
public class RentController {
    private final RentalService rentalService;

    public RentController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rent")
    public CarRent rentCar(
            @RequestParam Long carId,
            @RequestParam Long customerId,
            @RequestParam String rentalDate,
            @RequestParam String returnDate) {
        return rentalService.rentCar(
                carId,
                customerId,
                LocalDate.parse(rentalDate),
                LocalDate.parse(returnDate)
        );
    }
}
