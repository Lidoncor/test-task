package com.project.shiftlabtest.abstracts;

import com.project.shiftlabtest.models.Manufacturer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@MappedSuperclass
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "Поле не должно быть пустым")
    @Range(max = Integer.MAX_VALUE, message = "Некорректное значение")
    private Integer serialNumber;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @NotNull(message = "Поле не должно быть пустым")
    @Range(max = Integer.MAX_VALUE, message = "Некорректное значение")
    private Integer stockQuantity;

    @NotNull(message = "Поле не должно быть пустым")
    @Range(max = Integer.MAX_VALUE, message = "Некорректное значение")
    private Integer price;

    public Product() {
    }

    public Product(Integer serialNumber, Manufacturer manufacturer, Integer stockQuantity, Integer price) {
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
