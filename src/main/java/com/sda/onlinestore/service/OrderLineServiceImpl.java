package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.OrderLineDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.model.*;
import com.sda.onlinestore.repository.CartRepository;
import com.sda.onlinestore.repository.OrderLineRepository;
import com.sda.onlinestore.repository.OrderRepository;
import com.sda.onlinestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public void deleteOrderLineFromCart(Long id) {
        CartModel cartModel = cartRepository.findByUserAccountModel_Id(1L);
        List<OrderLineModel> orderLineModelList = cartModel.getOrderLineModelList();
        Optional<OrderLineModel> orderLineModelOptional = orderLineRepository.findById(id);
        if (orderLineModelOptional.isPresent()){
            OrderLineModel orderLineModel = orderLineModelOptional.get();
            orderLineModelList.remove(orderLineModel);
        }
        cartModel.setOrderLineModelList(orderLineModelList);
        double totalCost = 0.0;
        for(OrderLineModel orderLineModel2: orderLineModelList){
            totalCost += orderLineModel2.getLinePrice();
        }
        cartModel.setTotalCost(totalCost);
        cartRepository.save(cartModel);

    }

    @Override
    public void updateOrderLine(OrderLineDto orderLineDto) {
        Optional<OrderLineModel> orderLineModelOptional = orderLineRepository.findById(orderLineDto.getId());
        if (orderLineModelOptional.isPresent()) {
            OrderLineModel orderLineModel = orderLineModelOptional.get();

            OrderLineModel orderLineModel1 = new OrderLineModel();
            orderLineModel1.setProductModel(orderLineModel.getProductModel());
            orderLineModel1.setProductPrice(orderLineModel.getProductPrice());
            orderLineModel1.setQuantity(orderLineDto.getQuantity());
            orderLineModel1.setLinePrice((orderLineModel1.getProductPrice() * orderLineModel1.getQuantity()));

            CartModel cartModel = cartRepository.findByUserAccountModel_Id(1L);
            List<OrderLineModel> orderLineModelList = cartModel.getOrderLineModelList();
            orderLineModelList.add(orderLineModel1);
            orderLineModelList.remove(orderLineModel);
            cartModel.setOrderLineModelList(orderLineModelList);
            double totalCost = 0.0;
            for(OrderLineModel orderLineModel2: orderLineModelList){
                totalCost += orderLineModel2.getLinePrice();
            }
            cartModel.setTotalCost(totalCost);

            orderLineRepository.save(orderLineModel1);
            orderLineRepository.delete(orderLineModel);
        }
    }

        @Override
    public OrderLineDto getOrderLineById(Long id) {
        OrderLineDto orderLineDto = new OrderLineDto();
        Optional<OrderLineModel> orderLineModelOptional = orderLineRepository.findById(id);
        if (orderLineModelOptional.isPresent()) {
            OrderLineModel orderLineModel = orderLineModelOptional.get();
            orderLineDto.setId(orderLineModel.getId());

            ProductDto productDto = new ProductDto();
            ProductModel productModel = orderLineModel.getProductModel();
            productDto.setId(productModel.getId());
            productDto.setTitle(productModel.getTitle());
            productDto.setPrice(productModel.getPrice());
            orderLineDto.setProductDto(productDto);
            orderLineDto.setQuantity(orderLineModel.getQuantity());
            orderLineDto.setProductPrice(orderLineModel.getProductPrice());
            orderLineDto.setLinePrice(orderLineDto.getProductPrice() * orderLineDto.getQuantity());
        }
        return orderLineDto;
    }

}

