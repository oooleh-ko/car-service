package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.dto.CarRentView;
import com.collegesoft.carservice.model.CarRent;
import com.collegesoft.carservice.services.RentalService;
import com.collegesoft.carservice.dto.RentCarRequest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/rent")
    public List<CarRentView> rentCar(@RequestParam String city) {
        return rentalService.getRentsByCity(city);
    }
}
