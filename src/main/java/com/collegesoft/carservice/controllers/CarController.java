package com.collegesoft.carservice.controllers;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.repositories.CarRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/car")
public class CarController {
    private final CarRepository repo;

    public CarController(CarRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public Car get(@PathVariable Long id) {
        return this.repo.findById(id).orElse(null);
    }

    @GetMapping()
    public Iterable<Car> getAll() {
        return this.repo.findAll();
    }

    @PostMapping()
    public Car postCar(@RequestBody Car car) {
        return this.repo.save(car);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        return this.repo.save(car);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        this.repo.deleteById(id);
        return true;
    }
}
