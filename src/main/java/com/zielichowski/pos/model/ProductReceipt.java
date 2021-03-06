package com.zielichowski.pos.model;

import com.zielichowski.pos.devices.output.Printable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 27-Mar-17.
 */
@AllArgsConstructor
@NoArgsConstructor
public class ProductReceipt implements Receipt<Product> {

    private List<Product> productList = new ArrayList<>();

    public void addToReceipt(Product item) {
        productList.add(item);
    }

    public Double getTotal() {
        return productList.stream().mapToDouble(Product::getPrice).sum();
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder(100);
        productList.forEach(p -> stringBuilder.append(p.getName()).append("\t").append(p.getPrice()).append("\n"));
        stringBuilder.append(getTotal());
        return stringBuilder.toString();
    }
}
