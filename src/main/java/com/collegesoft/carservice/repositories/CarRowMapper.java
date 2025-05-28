package com.collegesoft.carservice.repositories;

import com.collegesoft.carservice.model.Car;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class CarRowMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(rs.getLong("id"));
        car.setProductionYear(rs.getInt("production_year"));
        car.setVinCode(rs.getString("vin_code"));
        car.setImageUrl(rs.getString("image_url"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));

        // Handle timestamps
        java.sql.Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
        if (createdAtTimestamp != null) {
            car.setCreatedAt(createdAtTimestamp.toLocalDateTime());
        }

        java.sql.Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");
        if (updatedAtTimestamp != null) {
            car.setUpdatedAt(updatedAtTimestamp.toLocalDateTime());
        }

        return car;
    }
}
