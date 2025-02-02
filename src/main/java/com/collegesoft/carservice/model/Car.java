package com.collegesoft.carservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car extends BaseEntity {
    private String brand;
    private String model;
    private String vinCode;
    private int productionYear;
    private String imageUrl;
}
