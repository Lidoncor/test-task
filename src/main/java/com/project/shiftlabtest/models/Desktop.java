package com.project.shiftlabtest.models;

import com.project.shiftlabtest.abstracts.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Desktop extends Product {

    @ManyToOne
    @JoinColumn(name = "formfactor_id")
    FormFactor formFactor;

    public Desktop() {

    }

    public Desktop(Integer serialNumber, Manufacturer manufacturer, Integer stockQuantity, Integer price, FormFactor formFactor) {
        super(serialNumber, manufacturer, stockQuantity, price);
        this.formFactor = formFactor;
    }

    public FormFactor getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(FormFactor formFactor) {
        this.formFactor = formFactor;
    }
}
