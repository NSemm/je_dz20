package com.k7.run;

import com.k7.entities.Order;
import com.k7.decorators.OrderDecorator;
import com.k7.decorators.PrintOrderDecorator;
import com.k7.decorators.SaveOrderDecorator;
import com.k7.decorators.ValidateOrderDecorator;
import com.k7.handlers.OrderHandler;
import com.k7.handlers.PrintOrderHandler;
import com.k7.handlers.SaveOrderHandler;
import com.k7.handlers.ValidateOrderHandler;

public class ExecRun {
    public void Chain(Order order) {
        OrderHandler chain = new ValidateOrderHandler();
        OrderHandler save = new SaveOrderHandler();
        OrderHandler print = new PrintOrderHandler();
        chain.setNext(save);
        save.setNext(print);
        chain.handler(order);
    }

    public void Decorator(Order order) {
        OrderDecorator decorator = new PrintOrderDecorator();
        decorator = new ValidateOrderDecorator(new SaveOrderDecorator(decorator));
        decorator.Proc(order);
    }
}
