package com.collegesoft.carservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Car extends BaseEntity {
    private int productionYear;
    private String vinCode;
    private String imageUrl;
    private String brand;
    private String model;
}
