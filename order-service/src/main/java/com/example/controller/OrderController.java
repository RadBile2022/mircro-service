package com.example.controller;


import com.example.dto.OrderResponse;
import com.example.entity.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable("id") Long id){
        return orderService.findById(id);
    }

    @PostMapping
    public Order save (@RequestBody Order order){
        return orderService.save(order);
        // ini kalau gagal save, id kepakai, meski dah kerollback,
        // jadi langsung next dari 5 ke 7
    }


    @GetMapping("/order-number/{number}")
    public OrderResponse findByOrderNumber(@PathVariable("number") String number){
        return orderService.findByOrderNumber(number);
    }

}
