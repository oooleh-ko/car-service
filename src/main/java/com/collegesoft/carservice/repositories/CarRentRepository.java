package com.collegesoft.carservice.repositories;

import com.collegesoft.carservice.dto.CarRentView;
import com.collegesoft.carservice.model.CarRent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CarRentRowMapper rowMapper = new CarRentRowMapper();

    public CarRentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<CarRent> findById(Long id) {
        String sql = "SELECT * FROM car_rent WHERE id = ?";
        List<CarRent> results = jdbcTemplate.query(sql, rowMapper, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public List<CarRent> findAll() {
        return jdbcTemplate.query("SELECT * FROM car_rent", rowMapper);
    }

    public CarRent save(CarRent carRent) {
        if (carRent.getId() == null) {
            return insert(carRent);
        }
        return update(carRent);
    }

    private CarRent insert(CarRent carRent) {
        String sql = "INSERT INTO car_rent (car_id, customer_id, start_date, end_date, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime now = LocalDateTime.now();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, carRent.getCarId());
            ps.setLong(2, carRent.getCustomerId());
            ps.setDate(3, Date.valueOf(carRent.getStartDate()));
            ps.setDate(4, Date.valueOf(carRent.getEndDate()));
            ps.setTimestamp(5, Timestamp.valueOf(now));
            ps.setTimestamp(6, Timestamp.valueOf(now));
            return ps;
        }, keyHolder);

        carRent.setId(keyHolder.getKey().longValue());
        carRent.setCreatedAt(now);
        carRent.setUpdatedAt(now);
        return carRent;
    }

    private CarRent update(CarRent carRent) {
        String sql = "UPDATE car_rent SET car_id = ?, customer_id = ?, start_date = ?, end_date = ?, updated_at = ? " +
                    "WHERE id = ?";
        
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(sql,
            carRent.getCarId(),
            carRent.getCustomerId(),
            Date.valueOf(carRent.getStartDate()),
            Date.valueOf(carRent.getEndDate()),
            Timestamp.valueOf(now),
            carRent.getId()
        );
        
        carRent.setUpdatedAt(now);
        return carRent;
    }

    public List<CarRentView> findCarRentsByCity(String city) {

        String sql =
                "SELECT r.id AS rent_id, " +
                        "c.brand, c.model, c.city_based, " +
                        "cu.name AS customer_name, cu.email AS customer_email, " +
                        "r.start_date, r.end_date " +
                        "FROM car_rent r " +
                        "JOIN car c ON r.car_id = c.id " +
                        "JOIN customer cu ON r.customer_id = cu.id " +
                        "WHERE c.city_based = ? ORDER BY r.created_at DESC LIMIT 100";

        return jdbcTemplate.query(sql, new Object[]{city}, (rs, rowNum) -> {
            CarRentView view = new CarRentView();
            view.setRentId(rs.getLong("rent_id"));
            view.setBrand(rs.getString("brand"));
            view.setModel(rs.getString("model"));
            view.setCity(rs.getString("city_based"));
            view.setCustomerName(rs.getString("customer_name"));
            view.setCustomerEmail(rs.getString("customer_email"));
            view.setStartDate(rs.getDate("start_date"));
            view.setEndDate(rs.getDate("end_date"));
            return view;
        });
    }


    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM car_rent WHERE id = ?", id);
    }
} 