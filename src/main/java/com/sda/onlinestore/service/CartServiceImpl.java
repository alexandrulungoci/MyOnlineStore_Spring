package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.*;
import com.sda.onlinestore.model.CartModel;
import com.sda.onlinestore.model.OrderLineModel;
import com.sda.onlinestore.model.ProductModel;
import com.sda.onlinestore.model.UserAccountModel;
import com.sda.onlinestore.repository.CartRepository;
import com.sda.onlinestore.repository.ProductRepository;
import com.sda.onlinestore.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    ProductRepository productRepository;

    private double totalCost = 0.0;

    @Override
    public void addToCart(Long userId, Long productId) {
        Optional<UserAccountModel> optionalUserAccountModel = userAccountRepository.findById(userId);
        if (optionalUserAccountModel.isPresent()) {
            UserAccountModel userAccountModel = optionalUserAccountModel.get();
            CartModel cartModel = userAccountModel.getCart();
            if (cartModel == null || cartModel.getOrderLineModelList() == null) {
                cartModel = new CartModel();
                cartModel.setTotalCost(totalCost);
                userAccountModel.setCart(cartModel);
            }

            OrderLineModel orderLineModel = new OrderLineModel();
            Optional<ProductModel> productModelOptional = productRepository.findById(productId);

            if (productModelOptional.isPresent()) {
                ProductModel productModel = productModelOptional.get();
                orderLineModel.setProductModel(productModel);
                orderLineModel.setQuantity(1);
                orderLineModel.setProductPrice(productModel.getPrice());
                orderLineModel.setLinePrice(orderLineModel.getQuantity() * orderLineModel.getProductPrice());

            }

            totalCost = cartModel.getTotalCost() + orderLineModel.getLinePrice();
            cartModel.setTotalCost(totalCost);
            cartModel.getOrderLineModelList().add(orderLineModel);
            cartRepository.save(cartModel);
            userAccountRepository.save(userAccountModel);
        }
    }

    @Override
    public CartDto findByUserAccountModel_Id(Long id) {
        CartDto cartDto = new CartDto();
        CartModel cartModel = cartRepository.findByUserAccountModel_Id(id);
        List<OrderLineDto> orderLineDtoList = new ArrayList<>();
        List<OrderLineModel> orderLineModelList = cartModel.getOrderLineModelList();
        if (orderLineModelList == null) {
            return null;
        }
        for (OrderLineModel orderLineModel : orderLineModelList) {
            OrderLineDto orderLineDto = new OrderLineDto();
            orderLineDto.setId(orderLineModel.getId());

            ProductDto productDto = new ProductDto();
            ProductModel productModel = orderLineModel.getProductModel();
            productDto.setId(productModel.getId());
            productDto.setTitle(productModel.getTitle());
            productDto.setThumbnail(productModel.getThumbnail());

            productDto.setPrice(productModel.getPrice());
            productDto.setProductType(productModel.getProductType());
            orderLineDto.setProductDto(productDto);
            orderLineDto.setQuantity(orderLineModel.getQuantity());
            orderLineDto.setProductPrice(orderLineModel.getProductPrice());
            orderLineDto.setLinePrice(orderLineModel.getQuantity() * orderLineModel.getProductPrice());
            orderLineDtoList.add(orderLineDto);
        }

        UserAccountDto userAccountDto = new UserAccountDto();
        Optional<UserAccountModel> userAccountModelOPtional = userAccountRepository.findById(id);
        if (userAccountModelOPtional.isPresent()) {
            UserAccountModel userAccountModel = userAccountModelOPtional.get();
            userAccountDto.setId(userAccountModel.getId());
            userAccountDto.setLogin(userAccountModel.getLogin());
            userAccountDto.setCity(userAccountModel.getCity());
            userAccountDto.setLogotype(userAccountModel.getLogotype());
            userAccountDto.setRoletype(userAccountModel.getRoletype());
        }

        cartDto.setId(cartModel.getId());
        cartDto.setOrderLineDtoList(orderLineDtoList);
        cartDto.setUserAccountDto(userAccountDto);
        cartDto.setTotalCost(cartModel.getTotalCost());

        return cartDto;
    }

}
