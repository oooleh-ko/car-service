package com.collegesoft.carservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CarServiceApplication {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	void runAfterStartup() {
		// Simple dev data database initialization.
		// Not intended for production use.
		createTables();
		insertInitialData();
	}

	void createTables() {
		// Create car table if not exists
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS car(" +
				"id SERIAL PRIMARY KEY," +
				"brand VARCHAR(255)," +
				"model VARCHAR(255)," +
				"vin_code VARCHAR(255)," +
				"city_based VARCHAR(255)," +
				"production_year INTEGER," +
				"image_url VARCHAR(255)," +
				"created_at TIMESTAMP," +
				"updated_at TIMESTAMP" +
				")");

		// Create customer table if not exists
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS customer(" +
				"id SERIAL PRIMARY KEY," +
				"name VARCHAR(255)," +
				"email VARCHAR(255)," +
				"rent_amount_times INTEGER," +
				"created_at TIMESTAMP," +
				"updated_at TIMESTAMP" +
				")");

		// Create car_rent table if not exists
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS car_rent(" +
				"id SERIAL PRIMARY KEY," +
				"car_id BIGINT REFERENCES car(id)," +
				"customer_id BIGINT REFERENCES customer(id)," +
				"start_date DATE," +
				"end_date DATE," +
				"created_at TIMESTAMP," +
				"updated_at TIMESTAMP" +
				")");
	}

	void insertInitialData() {
		if (!existsByVin("5342534534ae543")) {
			jdbcTemplate.update(
							"INSERT INTO car (production_year, vin_code, image_url, brand, model, city_based, created_at, " +
											"updated_at) " +
											"VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
							2005, "5342534534ae543", null, "opel", "astra", "New York"
			);
		}
		if (!existsEmail("petro@gmail.com")) {
			jdbcTemplate.update(
							"INSERT INTO customer (name, email, rent_amount_times, created_at, updated_at) " +
											"VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)",
							"petro", "petro@gmail.com", 0
			);
		}
	}

	private boolean existsByVin(String vinCode) {
		Integer count = jdbcTemplate.queryForObject(
						"SELECT COUNT(*) FROM car WHERE vin_code = ?",
						Integer.class,
						vinCode
		);
		return count != null && count > 0;
	}
	private boolean existsEmail(String email) {
		Integer count = jdbcTemplate.queryForObject(
						"SELECT COUNT(*) FROM customer WHERE email = ?",
						Integer.class,
						email
		);
		return count != null && count > 0;
	}
}
