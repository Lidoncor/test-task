package com.project.shiftlabtest.models;

import com.project.shiftlabtest.abstracts.Product;
import jakarta.persistence.Entity;

@Entity
public class Hdd extends Product {

    Integer capacity;

    public Hdd() {

    }

    public Hdd(Integer serialNumber, Manufacturer manufacturer, Integer stockQuantity, Integer price, Integer capacity) {
        super(serialNumber, manufacturer, stockQuantity, price);
        this.capacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
