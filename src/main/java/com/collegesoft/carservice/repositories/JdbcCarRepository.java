package com.collegesoft.carservice.repositories;

import com.collegesoft.carservice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JdbcCarRepository implements CarRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CarRowMapper carRowMapper;

    @Autowired
    public JdbcCarRepository(JdbcTemplate jdbcTemplate, CarRowMapper carRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.carRowMapper = carRowMapper;
    }

    @Override
    public Optional<Car> findById(Long id) {
        String sql = "SELECT * FROM car WHERE id = ?";
        List<Car> cars = jdbcTemplate.query(sql, carRowMapper, id);
        return cars.isEmpty() ? Optional.empty() : Optional.of(cars.get(0));
    }

    @Override
    public Iterable<Car> findAll() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, carRowMapper);
    }

    @Override
    public Car save(Car car) {
        if (car.getId() == null) {
            return insertCar(car);
        } else {
            return updateCar(car);
        }
    }

    private Car insertCar(Car car) {
        String sql = "INSERT INTO car (production_year, vin_code, image_url, brand, model, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        LocalDateTime now = LocalDateTime.now();
        car.setCreatedAt(now);
        car.setUpdatedAt(now);
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, car.getProductionYear());
            ps.setString(2, car.getVinCode());
            ps.setString(3, car.getImageUrl());
            ps.setString(4, car.getBrand());
            ps.setString(5, car.getModel());
            ps.setTimestamp(6, Timestamp.valueOf(car.getCreatedAt()));
            ps.setTimestamp(7, Timestamp.valueOf(car.getUpdatedAt()));
            return ps;
        }, keyHolder);
        
        car.setId(keyHolder.getKey().longValue());
        return car;
    }

    private Car updateCar(Car car) {
        String sql = "UPDATE car SET production_year = ?, vin_code = ?, image_url = ?, " +
                "brand = ?, model = ?, updated_at = ? WHERE id = ?";
        
        car.setUpdatedAt(LocalDateTime.now());
        
        jdbcTemplate.update(sql,
                car.getProductionYear(),
                car.getVinCode(),
                car.getImageUrl(),
                car.getBrand(),
                car.getModel(),
                Timestamp.valueOf(car.getUpdatedAt()),
                car.getId());
        
        return car;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM car WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}