package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.repositories.CarRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    public CarService(CarRepository repo) {
        this.repo = repo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Car car = new Car();
        car.setProductionYear(2020);
        this.repo.save(car);
        car = new Car();
        car.setProductionYear(2015);
        this.repo.save(car);
        car = new Car();
        car.setProductionYear(2022);
        this.repo.save(car);
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

    public Car update(Long id, Car car) {
        car.setId(id);
        return this.repo.save(car);
    }

    public boolean delete(Long id) {
        this.repo.deleteById(id);
        return true;
    }
}
