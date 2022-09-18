package com.acs.elearn.web.controllers;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.Order;
import com.acs.elearn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/placeOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String placeOrder() {
        return orderService.placeOrder();
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Order> all() {
        return orderService.allOrders();
    }
}
