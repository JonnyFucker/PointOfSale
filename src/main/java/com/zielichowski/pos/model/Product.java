package com.zielichowski.pos.model;

import com.zielichowski.pos.devices.output.Printable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Tomek on 27-Mar-17.
 */
@AllArgsConstructor
public class Product implements Printable {
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private Double price;
    @Getter
    private String barcode;

    public String print() {
        return this.name + " \t" + this.price;
    }
}
