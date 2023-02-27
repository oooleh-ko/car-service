package com.collegesoft.carservice;

import com.collegesoft.carservice.model.Car;
import com.collegesoft.carservice.model.Customer;
import com.collegesoft.carservice.repositories.CarRepository;
import com.collegesoft.carservice.services.PriceService;
import com.collegesoft.carservice.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {
	@InjectMocks
	public PriceService priceService;
	@Mock
	public UserService userService;

	private Car car;
	private Customer customer;


	@BeforeEach
	public void prepare() {
		this.customer = new Customer();
		when(this.userService.getDiscountPercentForCustomer(any())).thenReturn(0);
	}

	@Test
	void verifyPriceServiceIsCallingServiceForCalculatingUserDiscount1() {
		int finalPrice = this.priceService.getPriceForCarDailyRent(1L, customer);

		Assert.isTrue(finalPrice == 85, "");
		verify(userService, times(1)).getDiscountPercentForCustomer(any());
	}

	@Test
	void verifyPriceServiceIsCallingServiceForCalculatingUserDiscount2() {
		int finalPrice = this.priceService.getPriceForCarDailyRent(2L, customer);

		Assert.isTrue(finalPrice == 110, "");
		verify(userService, times(1)).getDiscountPercentForCustomer(any());
	}

	@Test
	void verifyPriceServiceIsCallingServiceForCalculatingUserDiscount3() {
		int finalPrice = this.priceService.getPriceForCarDailyRent(3L, customer);

		Assert.isTrue(finalPrice == 100, "");

		verify(userService, times(1)).getDiscountPercentForCustomer(any());
	}
}
