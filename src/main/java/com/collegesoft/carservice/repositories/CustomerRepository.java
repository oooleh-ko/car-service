package com.collegesoft.carservice.repositories;

import com.collegesoft.carservice.model.Customer;
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
public class CustomerRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper rowMapper = new CustomerRowMapper();

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Customer> findById(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        List<Customer> results = jdbcTemplate.query(sql, rowMapper, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * FROM customer", rowMapper);
    }

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            return insert(customer);
        }
        return update(customer);
    }

    private Customer insert(Customer customer) {
        String sql = "INSERT INTO customer (name, email, rent_amount_times, created_at, updated_at) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime now = LocalDateTime.now();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setInt(3, customer.getRentAmountTimes());
            ps.setTimestamp(4, Timestamp.valueOf(now));
            ps.setTimestamp(5, Timestamp.valueOf(now));
            return ps;
        }, keyHolder);

        customer.setId(keyHolder.getKey().longValue());
        customer.setCreatedAt(now);
        customer.setUpdatedAt(now);
        return customer;
    }

    private Customer update(Customer customer) {
        String sql = "UPDATE customer SET name = ?, email = ?, rent_amount_times = ?, updated_at = ? WHERE id = ?";
        
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(sql,
            customer.getName(),
            customer.getEmail(),
            customer.getRentAmountTimes(),
            Timestamp.valueOf(now),
            customer.getId()
        );
        
        customer.setUpdatedAt(now);
        return customer;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM customer WHERE id = ?", id);
    }
}

