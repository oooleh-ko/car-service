package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PriceService {
    public final int BASE_PRICE = 100;
    public UserService userService;
    public CarService carService;

    public int getPriceForCarDailyRent(long carId,  Long userId) {
        double customerDiscount = this.userService.getDiscountPercentForCustomer(new Customer());

        int carProductionYear =
                Objects.requireNonNull(this.carService.findById(carId).orElse(null)).getProductionYear();
        int currentYear = Year.now().getValue();
        double carDiscount;
        if (currentYear - carProductionYear < 2) {
            carDiscount = -0.1;
        } else if (currentYear - carProductionYear > 10) {
            carDiscount = 0.15;
        } else {
            carDiscount = 0;
        }

        double totalDiscount = customerDiscount + carDiscount;

        return (int) Math.round(BASE_PRICE * (1 - totalDiscount));
    }
}
