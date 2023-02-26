package com.collegesoft.carservice;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.model.Customer;
import com.collegesoft.carservice.services.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PriceServiceTest {
	@Autowired
	public PriceService priceService;

	@BeforeEach
	public void prepare() {
		System.out.println("BeforeEach");
	}

	@Test
	void contextLoads() {
		Car car = new Car();
		Customer customer = new Customer();
		this.priceService.getPriceForCar(car, customer);
	}
}
