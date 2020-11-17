package com.sda.onlinestore.controller;

import com.sda.onlinestore.dto.OrderDto;
import com.sda.onlinestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("getOrders")
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<OrderDto> orderDtoList = orderService.getAllOrders();
        return new ResponseEntity(orderDtoList, HttpStatus.OK);
    }

    @PostMapping("addOrder/{id}")
    public ResponseEntity addOrder(@PathVariable("id") Long id) {
        orderService.addOrder(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getOrderById/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long id) {
        OrderDto orderDto = orderService.getOrderById(id);
        return new ResponseEntity(orderDto, HttpStatus.OK);
    }

    @GetMapping("getOrderByUserId/{id}")
    public ResponseEntity<OrderDto> getOrderByUserId(@PathVariable("id") Long id) {
        List<OrderDto> orderDtoList = orderService.getOrderByUserId(id);
        return new ResponseEntity(orderDtoList, HttpStatus.OK);
    }


//    @PutMapping("editOrder")
//    public ResponseEntity editOrder(@RequestBody OrderDto orderDto) {
//        orderService.updateOrder(orderDto);
//        return new ResponseEntity(HttpStatus.OK);
//    }

}
