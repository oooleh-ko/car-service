package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.model.Customer;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

@Service
public class PriceService {
    public final int BASE_PRICE = 100;
    public UserService userService;

    public PriceService(UserService userService) {
        this.userService = userService;
        cars.put(1L, new Car(){{setProductionYear(2005);}});
        cars.put(2L, new Car(){{setProductionYear(2022);}});
        cars.put(3L, new Car(){{setProductionYear(2015);}});
    }
//    public CarRepository carRepository;

    public Map<Long, Car> cars = new HashMap<>();

    public Car findCarById(long carId) {
        return cars.get(carId);
    }

    public int getPriceForCarDailyRent(long carId, Customer customer) {
        double customerDiscount = this.userService.getDiscountPercentForCustomer(new Customer());
        int carProductionYear = this.findCarById(carId).getProductionYear();
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
