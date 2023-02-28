package com.collegesoft.carservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {
    private int rentAmountTimes;
}
