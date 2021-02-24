package com.k7.handlers;

import com.k7.entities.Order;
import lombok.Setter;

@Setter
public abstract class OrderHandler {
    private OrderHandler next;

   public abstract void handler(Order o);

    public void next(Order o) {
        if (next != null) next.handler(o);
    }
}
