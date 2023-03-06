package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.services.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/price")
public class PriceController {
    PriceService priceService;
    @GetMapping(value = "/calculate/{id}")
    public int getPrice(@PathVariable Long carId) {
        return this.priceService.getPriceForCarDailyRent(carId, null);
    }
}
