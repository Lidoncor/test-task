package com.project.shiftlabtest.models;

import com.project.shiftlabtest.abstracts.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Laptop extends Product {

    @ManyToOne
    @JoinColumn(name = "laptopsize_id")
    LaptopSize laptopSize;

    public Laptop() {

    }

    public Laptop(Integer serialNumber, Manufacturer manufacturer, Integer stockQuantity, Integer price, LaptopSize laptopSize) {
        super(serialNumber, manufacturer, stockQuantity, price);
        this.laptopSize = laptopSize;
    }

    public LaptopSize getLaptopSize() {
        return laptopSize;
    }

    public void setLaptopSize(LaptopSize laptopSize) {
        this.laptopSize = laptopSize;
    }
}
