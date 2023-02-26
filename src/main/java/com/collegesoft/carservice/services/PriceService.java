package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.model.Customer;
import com.collegesoft.carservice.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceService {
    public UserService userService;
    public CarRepository carRepository;
    public int getPriceForCar(Car car, Customer customer) {
        this.userService.getDiscountPercentForCustomer(new Customer());
        Optional<Car> c = this.carRepository.findById(21L);
        return 0;
    }
}
