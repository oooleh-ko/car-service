package com.collegesoft.carservice.repositories;

import com.collegesoft.carservice.model.CarRent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<CarRent, Long> {
}
