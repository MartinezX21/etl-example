package org.uni2growcm.camel;

import java.util.Date;

public class SaleOutput {
    private String name;
    private Double unitPrice;
    private Integer quantity;
    private Double totalPrice;
    private String saleDate;

    public SaleOutput() {
    }

    public SaleOutput(String name, Double unitPrice, Integer quantity, Double totalPrice, String saleDate) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }
}
