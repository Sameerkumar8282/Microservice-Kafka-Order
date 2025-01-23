package com.microservice.order_service.controller;

import com.microservice.basedomains.DTO.Order;
import com.microservice.basedomains.DTO.OrderEvent;
import com.microservice.order_service.kafka.OrderProducer;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody  Order order){

        order.setOrderId(UUID.randomUUID().toString());
        order.setName("Mobile");
        order.setQty(10);

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order status is Pending!");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);
        return "Order Placed SuccessFully";
    }
}
