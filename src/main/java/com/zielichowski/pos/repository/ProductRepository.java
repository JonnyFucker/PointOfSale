package com.zielichowski.pos.repository;

import com.zielichowski.pos.model.Product;

/**
 * Created by Tomek on 27-Mar-17.
 */
public interface ProductRepository {
    Product findByBarcode(String barcode);
}
