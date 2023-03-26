package com.project.shiftlabtest.models;

import com.project.shiftlabtest.abstracts.Product;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@Entity
public class Hdd extends Product {

    @NotNull(message = "Поле не должно быть пустым")
    @Range(max = Integer.MAX_VALUE, message = "Некорректное значение")
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
