package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.CartDto;

public interface CartService {

    CartDto findByUserAccountModel_Id(Long id);

    void addToCart(Long userId, Long productId);

}
