package com.collegesoft.carservice.services;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.model.CarRent;
import com.collegesoft.carservice.model.Customer;
import com.collegesoft.carservice.repositories.CarRepository;
import com.collegesoft.carservice.repositories.CustomerRepository;
import com.collegesoft.carservice.repositories.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    private final CarRepository carRepository;

    private final CustomerRepository customerRepository;

    public RentalService(RentalRepository rentalRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public CarRent rentCar(Long carId, Long customerId, LocalDate rentalDate, LocalDate returnDate) {
        Optional<Car> car = carRepository.findById(carId);
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (car.isPresent() && customer.isPresent()) {
            CarRent rental = new CarRent();
            rental.setCar(car.get());
            rental.setCustomer(customer.get());
            rental.setStartDate(rentalDate);
            rental.setEndDate(returnDate);
            return rentalRepository.save(rental);
        } else {
            throw new RuntimeException("Car or Customer not found");
        }
    }
}
