package org.uni2growcm.camel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SaleItem {
    @JsonProperty("product")
    private String product;
    @JsonProperty("unitPrice")
    private Double unitPrice;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("totalPrice")
    private Double totalPrice;
    @JsonProperty("saleDate")
    private Date saleDate;

    public SaleItem() {
    }

    public SaleItem(String product, Double unitPrice, Integer quantity, Double totalPrice, Date saleDate) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "product='" + product + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", saleDate=" + saleDate +
                '}';
    }
}
