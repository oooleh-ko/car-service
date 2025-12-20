package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.repositories.CarRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository repo;

    public CarService(CarRepository repo) {
        this.repo = repo;
    }

    public Optional<Car> findById(Long id) {
        return this.repo.findById(id);
    }

    public List<Car> findAll() {
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

  public Iterable<Car> findByCity(String city) {
    return this.repo.findByCity(city);
  }
}
