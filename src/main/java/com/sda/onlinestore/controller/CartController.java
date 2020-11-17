package com.sda.onlinestore.controller;

import com.sda.onlinestore.dto.CartDto;
import com.sda.onlinestore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("findByUserAccountModel_Id/{id}")
    public ResponseEntity findByUserAccountModel_Id(@PathVariable("id") Long id) {
        CartDto cartDto = cartService.findByUserAccountModel_Id(id);
        return new ResponseEntity(cartDto, HttpStatus.OK);
    }

    @PutMapping("addToCart/{userId}/{productId}")
    public ResponseEntity addToCart(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        cartService.addToCart(userId, productId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
