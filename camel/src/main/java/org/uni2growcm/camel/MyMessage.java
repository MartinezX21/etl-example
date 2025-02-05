package org.uni2growcm.camel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MyMessage {
    @JsonProperty("greetings")
    private String greetings;
    @JsonProperty("saleItems")
    private List<SaleItem> saleItems;

    public MyMessage() {
    }

    public MyMessage(String greetings, List<SaleItem> saleItems) {
        this.greetings = greetings;
        this.saleItems = saleItems;
    }

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "greetings='" + greetings + '\'' +
                ", saleItems=" + saleItems +
                '}';
    }
}
