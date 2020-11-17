package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.CartDto;
import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.OrderDto;
import com.sda.onlinestore.model.OrderModel;

import java.util.List;

public interface OrderService {

    OrderDto getOrderById(Long id);

    List<OrderDto> getOrderByUserId(Long userId);

    List<OrderDto> getAllOrders();

    void addOrder(Long id);

//    void updateOrder(OrderDto orderDto);


}
