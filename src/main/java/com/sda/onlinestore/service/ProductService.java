package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProduct(Long id);

    List<ProductDto> getAllProducts();

    void deleteProduct(Long id);

    void addProduct(ProductDto productDto);

    void updateProduct(ProductDto productDto);
}
