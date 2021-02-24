package com.k7.decorators;

import com.k7.entities.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidateOrderDecorator implements OrderDecorator {
    private OrderDecorator wrapObj;

    @Override
    public void Proc(Order o) {
        if (checkOrder(o)) {
            System.out.println("invalid order");
            return;
        } else {
            wrapObj.Proc(o);
        }

    }

    private boolean checkOrder(Order o) {
        return (o.getId() == null || o.getFrom() == null || o.getDescription() == null);
    }
}
