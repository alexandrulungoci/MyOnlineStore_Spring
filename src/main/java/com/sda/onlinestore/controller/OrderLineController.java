package com.sda.onlinestore.controller;

import com.sda.onlinestore.dto.OrderLineDto;
import com.sda.onlinestore.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @DeleteMapping("deleteOrderLine/{id}")
    public ResponseEntity deleteOrderLine(@PathVariable("id") Long id) {
        orderLineService.deleteOrderLineFromCart(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("updateOrderLine")
    public ResponseEntity updateOrderLine(@RequestBody OrderLineDto orderLineDto) {
        orderLineService.updateOrderLine(orderLineDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getOrderLineById/{id}")
    public ResponseEntity<OrderLineDto> getOrderLineById(@PathVariable("id") Long id) {
        OrderLineDto orderLineDto = orderLineService.getOrderLineById(id);
        return new ResponseEntity(orderLineDto, HttpStatus.OK);
    }
}
