package com.collegesoft.carservice.repositories;

import com.collegesoft.carservice.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CarRowMapper rowMapper = new CarRowMapper();

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Car> findById(Long id) {
        String sql = "SELECT * FROM car WHERE id = ?";
        List<Car> results = jdbcTemplate.query(sql, rowMapper, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public List<Car> findByCity(String city) {
        return jdbcTemplate.query(
                "SELECT * FROM car WHERE city_based = ?",
                rowMapper,
                city
        );
    }

    public List<Car> findAll() {
        return jdbcTemplate.query("SELECT * FROM car", rowMapper);
    }

    public Car save(Car car) {
        if (car.getId() == null) {
            return insert(car);
        }
        return update(car);
    }

    private Car insert(Car car) {
        String sql = "INSERT INTO car (brand, model, vin_code, production_year, image_url, city_based, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime now = LocalDateTime.now();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getVinCode());
            ps.setInt(4, car.getProductionYear());
            ps.setString(5, car.getImageUrl());
            ps.setString(6, car.getCityBased());
            ps.setTimestamp(6, Timestamp.valueOf(now));
            ps.setTimestamp(7, Timestamp.valueOf(now));
            return ps;
        }, keyHolder);

        car.setId(keyHolder.getKey().longValue());
        car.setCreatedAt(now);
        car.setUpdatedAt(now);
        return car;
    }

    private Car update(Car car) {
        String sql = "UPDATE car SET brand = ?, model = ?, vin_code = ?, " +
                    "production_year = ?, image_url = ?, updated_at = ? WHERE id = ?";
        
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(sql,
            car.getBrand(),
            car.getModel(),
            car.getVinCode(),
            car.getProductionYear(),
            car.getImageUrl(),
            Timestamp.valueOf(now),
            car.getId()
        );
        
        car.setUpdatedAt(now);
        return car;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM car WHERE id = ?", id);
    }
}

