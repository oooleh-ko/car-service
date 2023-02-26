package com.collegesoft.carservice.model;

import lombok.Getter;

@Getter
public class Car {
    private int productionYear;
    private String vinCode;
    private Customer owner;
}
