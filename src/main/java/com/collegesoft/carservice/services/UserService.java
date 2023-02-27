package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public int getDiscountPercentForCustomer(Customer customer) {
        int times = customer.getRentAmountTimes();
        if (times >= 2) {
            return 10;
        } else {
            return 0;
        }
    }
}
