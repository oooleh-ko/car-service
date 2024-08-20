package com.collegesoft.carservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


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
