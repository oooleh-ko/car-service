package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public double getDiscountPercentForCustomer(Customer customer) {
        int times = customer.getRentAmountTimes();
        if (times >= 2) {
            return 0.1;
        } else {
            return 0;
        }
    }
}
