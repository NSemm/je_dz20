package com.k7.decorators;

import com.k7.entities.Order;

public class PrintOrderDecorator implements OrderDecorator {
    @Override
    public void Proc(Order o) {
        System.out.println("Your order #" + o.getId() + " complete successfully!");
    }
}
