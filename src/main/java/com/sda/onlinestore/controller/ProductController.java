package com.sda.onlinestore.controller;


import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("getProducts")
    public ResponseEntity<List<ProductDto>> getProducts(){
       List<ProductDto> productList= productService.getAllProducts();
       System.out.println("productList: " + productList);
       return new ResponseEntity(productList, HttpStatus.OK);

    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("addProduct")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getProduct/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto productDto = productService.getProduct(id);
        return new ResponseEntity(productDto, HttpStatus.OK);

    }

    @PutMapping("editProduct")
    public ResponseEntity editProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
