package com.collegesoft.carservice;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.model.Customer;
import com.collegesoft.carservice.repositories.CarRepository;
import com.collegesoft.carservice.services.PriceService;
import com.collegesoft.carservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {
	@InjectMocks
	public PriceService priceService;
	@Mock
	public UserService userService;
	@Mock
	public CarRepository carRepository;
	private Car car;
	private Customer customer;

	@BeforeEach
	public void prepare() {
		this.car = new Car();
		this.customer = new Customer();
		when(this.userService.getDiscountPercentForCustomer(any())).thenReturn(10);
	}

	@Test
	void verifyPriceServiceIsCallingServiceForCalculatingUserDiscount() {
		this.priceService.getPriceForCar(car, customer);

		verify(userService, times(1)).getDiscountPercentForCustomer(any());
	}
}
