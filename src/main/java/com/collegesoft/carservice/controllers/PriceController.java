package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.services.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
@AllArgsConstructor
public class PriceController {
   public PriceService priceService;
    @GetMapping(value = "/calculate")
    public int getPrice() {
        return this.priceService.getPriceForCar(new Car(), null);
    }
}
