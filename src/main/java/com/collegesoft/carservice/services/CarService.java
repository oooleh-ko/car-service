package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.repositories.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    public CarService(CarRepository repo) {
        this.repo = repo;
    }

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

    @Transactional
    public Car update(Long id, Car car) {
        car.setId(id);
        return this.repo.save(car);
    }

    @Transactional
    public boolean delete(Long id) {
        this.repo.deleteById(id);
        return true;
    }
}
