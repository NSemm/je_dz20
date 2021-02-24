package com.k7.entities;

public class OrderCreate {
    public Order create() {
        Order order = new Order();
        order.setId("1235");
        order.setFrom("Maxim");
        order.setDescription("sdfklsjdflksmdclksmdclksmdvclksmndvlndfkjsndf;kajn;kfjan;gkjnsdfksjdcnsdk");
        return order;
    }
}
