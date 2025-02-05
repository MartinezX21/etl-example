package org.uni2growcm.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyCustomProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Object body = exchange.getMessage().getBody();
        if (body instanceof List) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<SaleItem> saleItems = new ArrayList<>();
            List<SaleOutput> saleOutputs = (List<SaleOutput>) exchange.getMessage().getBody();
            SaleItem saleItem;
            for (SaleOutput output: saleOutputs){
                saleItem = new SaleItem();
                saleItem.setProduct(output.getName());
                saleItem.setQuantity(output.getQuantity());
                saleItem.setUnitPrice(output.getUnitPrice());
                saleItem.setTotalPrice(output.getTotalPrice());
                saleItem.setSaleDate(dateFormat.parse(output.getSaleDate()));
                saleItems.add(saleItem);
            }
            exchange.getMessage().setBody(new MyMessage(null, saleItems));
        }
    }
}
