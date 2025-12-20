package com.collegesoft.carservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car extends BaseEntity {
    private String brand;
    private String model;
    private String vinCode;
    private String cityBased;
    private int productionYear;
    private String imageUrl;
}
