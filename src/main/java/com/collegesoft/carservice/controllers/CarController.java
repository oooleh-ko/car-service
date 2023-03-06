package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.services.CarService;
import org.springframework.web.bind.annotation.*;

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
    public Iterable<Car> getAll() {
        return this.carService.findAll();
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
