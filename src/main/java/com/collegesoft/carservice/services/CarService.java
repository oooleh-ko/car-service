package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository repo;


    public Optional<Car> findById(Long id) {
        return this.repo.findById(id);
    }

    public Iterable<Car> findAll() {
        return this.repo.findAll();
    }

    public Car save(Car car) {
        return this.repo.save(car);
    }

    public Car update(Long id, Car car) {
        car.setId(id);
        return this.repo.save(car);
    }

    public boolean delete(Long id) {
        this.repo.deleteById(id);
        return true;
    }
}
