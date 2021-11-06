package com.hashkart.orderManagement.controller;

import com.hashkart.orderManagement.model.OrderDetails;
import com.hashkart.orderManagement.repo.OrderRepo;
import com.hashkart.orderManagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepo orderRepo;


    @GetMapping("/orders")
    public ResponseEntity<List<OrderDetails>> getAll() {
        return ResponseEntity.ok().body(orderRepo.findAll());
    }

    @PostMapping("/orders")
    public OrderDetails save(@RequestBody OrderDetails orderDetails) {
        return orderRepo.save(orderDetails);
    }


}
