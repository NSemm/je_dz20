package com.k7.handlers;

import com.k7.entities.Order;

public class PrintOrderHandler extends OrderHandler {
    @Override
    public void handler(Order o) {
        System.out.println("Your order #"+o.getId()+" complete successfully!");
    }
}
