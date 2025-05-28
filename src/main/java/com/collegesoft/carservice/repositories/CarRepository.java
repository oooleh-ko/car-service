package com.collegesoft.carservice.repositories;

import com.collegesoft.carservice.model.Car;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository {
    Optional<Car> findById(Long id);
    Iterable<Car> findAll();
    Car save(Car car);
    void deleteById(Long id);
}
