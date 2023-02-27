package com.collegesoft.carservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private int productionYear;
    private String vinCode;
    private Customer owner;
}
