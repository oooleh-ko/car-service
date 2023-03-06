package com.collegesoft.carservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Car extends BaseEntity {
    private int productionYear;
    private String vinCode;
    private String imageUrl;
    private String brand;
    private String model;
}
