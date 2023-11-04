package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PriceService {
    public UserService userService;
    public int getPriceForCar(Car car, Customer customer) {
        this.userService.getDiscountPercentForCustomer(new Customer());
        return 0;
    }
}
