package com.k7;

import com.k7.entities.OrderCreate;
import com.k7.run.ExecRun;

public class Main {
    public static void main(String[] args) {
        OrderCreate orderCreate = new OrderCreate();
        ExecRun run = new ExecRun();
        //run.Chain(orderCreate.create());
        run.Decorator(orderCreate.create());


    }
}
