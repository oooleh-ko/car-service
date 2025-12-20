package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService repo) {
        this.carService = repo;
    }

    @GetMapping("/{id}")
    public Car get(@PathVariable Long id) {
        return this.carService.findById(id).orElse(null);
    }

    @GetMapping()
    public Iterable<Car> getAll(@RequestParam String city) {
        Iterable<Car> cars;
        System.out.println(Instant.now());
        if (city != null && !city.isEmpty()) {
            cars = this.carService.findByCity(city);
        } else {
            cars = this.carService.findAll();
        }
        System.out.println(Instant.now());
        return cars;
    }

    @PostMapping()
    public Car postCar(@RequestBody Car car) {
        return this.carService.save(car);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        return this.carService.update(id, car);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        this.carService.delete(id);
        return true;
    }
}
