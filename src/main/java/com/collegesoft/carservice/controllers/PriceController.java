package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.services.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/price")
public class PriceController {
    PriceService priceService;
    @GetMapping(value = "/calculate")
    public int getPrice() {
        return this.priceService.getPriceForCar(new Car(), null);
    }
}
