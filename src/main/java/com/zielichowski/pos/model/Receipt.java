package com.zielichowski.pos.model;

/**
 * Created by Tomek on 27-Mar-17.
 */
public interface Receipt<T>  {
    void addToReceipt(T item);
    Double getTotal();

}
