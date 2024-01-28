package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.services.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/price")
public class PriceController {
    PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping(value = "/calculate/{carId}")
    public int getPrice(@PathVariable int carId) {
        return this.priceService.getPriceForCarDailyRent(carId, null);
    }
}
