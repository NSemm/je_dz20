package com.k7.handlers;

import com.k7.entities.Order;

public class ValidateOrderHandler extends OrderHandler {
    @Override
    public void handler(Order o) {
        if (checkOrder(o)) {
            System.out.println("invalid order");
            return;
        } else {
            next(o);
        }

    }

    private boolean checkOrder(Order o) {
        return (o.getId() == null || o.getFrom() == null || o.getDescription() == null);
    }
}
