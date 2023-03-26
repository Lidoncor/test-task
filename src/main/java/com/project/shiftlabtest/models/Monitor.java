package com.project.shiftlabtest.models;

import com.project.shiftlabtest.abstracts.Product;
import jakarta.persistence.Entity;

@Entity
public class Monitor extends Product {

    Integer size;

    public Monitor() {

    }

    public Monitor(Integer serialNumber, Manufacturer manufacturer, Integer stockQuantity, Integer price, Integer size) {
        super(serialNumber, manufacturer, stockQuantity, price);
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
