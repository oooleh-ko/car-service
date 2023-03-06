package com.collegesoft.carservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;

@EnableJpaAuditing
@SpringBootApplication
public class CarServiceApplication {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}


	void runJDBC() {
		jdbcTemplate.execute("DROP TABLE car IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE car(" +
				"id SERIAL," +
				"brand VARCHAR(255)," +
				"model VARCHAR(255)," +
				"vin_code VARCHAR(255)," +
				"production_year INTEGER," +
				"image_url VARCHAR(255)," +
				"engine_volume NUMERIC," +
				"created_at TIMESTAMP," +
				"updated_at TIMESTAMP" +
				")");
	}
}
