package com.collegesoft.carservice;

import com.collegesoft.carservice.model.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarServiceApplication {

	public static void main(String[] args) {
		Car car = new Car();
		car.getProductionYear();
		SpringApplication.run(CarServiceApplication.class, args);
	}
}
