package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.OrderLineDto;

import java.util.List;

public interface OrderLineService {

    void deleteOrderLineFromCart(Long id);

    void updateOrderLine(OrderLineDto orderLineDto);

    OrderLineDto getOrderLineById(Long id);

}
