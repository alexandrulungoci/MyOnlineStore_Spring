package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.*;
import com.sda.onlinestore.model.*;
import com.sda.onlinestore.repository.CartRepository;
import com.sda.onlinestore.repository.OrderRepository;
import com.sda.onlinestore.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addOrder(Long id) {
        OrderModel orderModel = new OrderModel();

        CartModel cartModel = cartRepository.findByUserAccountModel_Id(id);
        List<OrderLineModel> orderLineModelList = cartModel.getOrderLineModelList();
        orderModel.setOrderLineModelList(orderLineModelList);

        Optional<UserAccountModel> userAccountModelOptional = userAccountRepository.findById(id);
        if (userAccountModelOptional.isPresent()) {
            UserAccountModel userAccountModel = userAccountModelOptional.get();
            orderModel.setUserAccountModel(userAccountModel);
            orderModel.setUserName(userAccountModel.getLogin());
            orderModel.setDeliveryAddress(userAccountModel.getDeliveryAdress());
            orderModel.setUserAddress(userAccountModel.getUserAdress());
        }
        orderModel.setTotalCost(cartModel.getTotalCost());
        orderModel.setStatus(OrderStatus.PROCESSING);
        cartModel.setOrderLineModelList(null);
        cartModel.setTotalCost(0.0);

        orderRepository.save(orderModel);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Optional<OrderModel> orderModelOptional = orderRepository.findById(id);

        if (orderModelOptional.isPresent()) {
            OrderModel orderModel = orderModelOptional.get();

            OrderDto orderDto = new OrderDto();

            orderDto.setId(orderModel.getId());
            orderDto.setTotalCost(orderModel.getTotalCost());

            List<OrderLineModel> orderLineModelList = orderModel.getOrderLineModelList();
            List<OrderLineDto> orderLineDtoList = new ArrayList<>();
            for (OrderLineModel orderLineModel : orderLineModelList) {
                OrderLineDto orderLineDto = new OrderLineDto();
                orderLineDto.setId(orderLineModel.getId());
                orderLineDto.setProductPrice(orderLineModel.getProductPrice());
                orderLineDto.setQuantity(orderLineModel.getQuantity());

                ProductModel productModel = orderLineModel.getProductModel();
                ProductDto productDto = new ProductDto();
                productDto.setId(productModel.getId());
                productDto.setPrice(productModel.getPrice());
                productDto.setThumbnail(productModel.getThumbnail());
                productDto.setTitle(productModel.getTitle());
                productDto.setProductType(productModel.getProductType());

                orderLineDto.setProductDto(productDto);
                orderLineDto.setLinePrice(orderLineModel.getLinePrice());

                orderLineDtoList.add(orderLineDto);
            }

            orderDto.setOrderLineDtoList(orderLineDtoList);
            orderDto.setOrderDate(orderModel.getOrderDate());
            orderDto.setStatus(orderModel.getStatus());

            UserAccountModel userAccountModel = orderModel.getUserAccountModel();
            UserAccountDto userAccountDto = new UserAccountDto();
            userAccountDto.setId(userAccountModel.getId());
            userAccountDto.setCity(userAccountModel.getCity());
            userAccountDto.setLogin(userAccountModel.getLogin());
            userAccountDto.setLogotype(userAccountModel.getLogotype());
            userAccountDto.setPassword(userAccountModel.getPassword());
            userAccountDto.setRoletype(userAccountModel.getRoletype());

            UserAdressModel deliveryAdressModel = userAccountModel.getDeliveryAdress();
            UserAddressDto deliveryAdressDto = new UserAddressDto();
            deliveryAdressDto.setZipcode(deliveryAdressModel.getZipcode());
            deliveryAdressDto.setStreet(deliveryAdressModel.getStreet());
            deliveryAdressDto.setId(deliveryAdressModel.getId());
            deliveryAdressDto.setCountry(deliveryAdressModel.getCountry());
            deliveryAdressDto.setCity(deliveryAdressModel.getCity());
            userAccountDto.setDeliveryAdress(deliveryAdressDto);

            UserAdressModel userAdressModel = userAccountModel.getUserAdress();
            UserAddressDto userAdressDto = new UserAddressDto();
            userAdressDto.setZipcode(userAdressModel.getZipcode());
            userAdressDto.setStreet(userAdressModel.getStreet());
            userAdressDto.setId(userAdressModel.getId());
            userAdressDto.setCountry(userAdressModel.getCountry());
            userAdressDto.setCity(userAdressModel.getCity());
            userAccountDto.setUserAdress(userAdressDto);

            orderDto.setDeliveryAddress(deliveryAdressDto);
            orderDto.setUserAddress(userAdressDto);

            orderDto.setUserAccountDto(userAccountDto);
            return orderDto;
        }
        return null;
    }

    @Override
    public List<OrderDto> getOrderByUserId(Long userId) {
        List<OrderModel> orderModelList = orderRepository.findByUserAccountModel_Id(userId);

        List<OrderDto> orderDtoList = new ArrayList<>();

        for (OrderModel orderModel : orderModelList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderModel.getId());
            orderDto.setTotalCost(orderModel.getTotalCost());

            List<OrderLineModel> orderLineModelList = orderModel.getOrderLineModelList();
            List<OrderLineDto> orderLineDtoList = new ArrayList<>();
            for (OrderLineModel orderLineModel : orderLineModelList) {
                OrderLineDto orderLineDto = new OrderLineDto();
                orderLineDto.setId(orderLineModel.getId());
                orderLineDto.setProductPrice(orderLineModel.getProductPrice());
                orderLineDto.setQuantity(orderLineModel.getQuantity());

                ProductModel productModel = orderLineModel.getProductModel();
                ProductDto productDto = new ProductDto();
                productDto.setId(productModel.getId());
                productDto.setPrice(productModel.getPrice());
                productDto.setThumbnail(productModel.getThumbnail());
                productDto.setTitle(productModel.getTitle());
                productDto.setProductType(productModel.getProductType());

                orderLineDto.setProductDto(productDto);
                orderLineDto.setLinePrice(orderLineModel.getLinePrice());

                orderLineDtoList.add(orderLineDto);
            }

            orderDto.setOrderLineDtoList(orderLineDtoList);
            orderDto.setOrderDate(orderModel.getOrderDate());
            orderDto.setStatus(orderModel.getStatus());

            UserAccountModel userAccountModel = orderModel.getUserAccountModel();
            UserAccountDto userAccountDto = new UserAccountDto();
            userAccountDto.setId(userAccountModel.getId());
            userAccountDto.setCity(userAccountModel.getCity());
            userAccountDto.setLogin(userAccountModel.getLogin());
            userAccountDto.setLogotype(userAccountModel.getLogotype());
            userAccountDto.setPassword(userAccountModel.getPassword());
            userAccountDto.setRoletype(userAccountModel.getRoletype());

            UserAdressModel deliveryAdressModel = userAccountModel.getDeliveryAdress();
            UserAddressDto deliveryAdressDto = new UserAddressDto();
            deliveryAdressDto.setZipcode(deliveryAdressModel.getZipcode());
            deliveryAdressDto.setStreet(deliveryAdressModel.getStreet());
            deliveryAdressDto.setId(deliveryAdressModel.getId());
            deliveryAdressDto.setCountry(deliveryAdressModel.getCountry());
            deliveryAdressDto.setCity(deliveryAdressModel.getCity());
            userAccountDto.setDeliveryAdress(deliveryAdressDto);

            UserAdressModel userAdressModel = userAccountModel.getUserAdress();
            UserAddressDto userAdressDto = new UserAddressDto();
            userAdressDto.setZipcode(userAdressModel.getZipcode());
            userAdressDto.setStreet(userAdressModel.getStreet());
            userAdressDto.setId(userAdressModel.getId());
            userAdressDto.setCountry(userAdressModel.getCountry());
            userAdressDto.setCity(userAdressModel.getCity());
            userAccountDto.setUserAdress(userAdressDto);

            orderDto.setDeliveryAddress(deliveryAdressDto);
            orderDto.setUserAddress(userAdressDto);
            orderDto.setUserAccountDto(userAccountDto);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }


    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderModel> orderModels = orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (OrderModel orderModel : orderModels) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderModel.getId());

            orderDto.setOrderDate(orderModel.getOrderDate());

            List<OrderLineModel> orderLineModelList = orderModel.getOrderLineModelList();
            List<OrderLineDto> orderLineDtoList = new ArrayList<>();
            for (OrderLineModel orderLineModel : orderLineModelList) {
                OrderLineDto orderLineDto = new OrderLineDto();
                orderLineDto.setId(orderLineModel.getId());
                orderLineDto.setProductPrice(orderLineModel.getProductPrice());
                orderLineDto.setQuantity(orderLineModel.getQuantity());

                ProductModel productModel = orderLineModel.getProductModel();
                ProductDto productDto = new ProductDto();
                productDto.setId(productModel.getId());
                productDto.setPrice(productModel.getPrice());
                productDto.setThumbnail(productModel.getThumbnail());
                productDto.setTitle(productModel.getTitle());
                productDto.setProductType(productModel.getProductType());

                orderLineDto.setProductDto(productDto);
                orderLineDto.setLinePrice(orderLineModel.getLinePrice());
                orderLineDtoList.add(orderLineDto);
            }

            orderDto.setOrderLineDtoList(orderLineDtoList);
            orderDto.setOrderDate(orderModel.getOrderDate());
            orderDto.setStatus(orderModel.getStatus());
            orderDto.setTotalCost(orderModel.getTotalCost());

            orderDto.setStatus(orderModel.getStatus());
            orderDto.setTotalCost(orderModel.getTotalCost());

            UserAccountModel userAccountModel = orderModel.getUserAccountModel();
            UserAccountDto userAccountDto = new UserAccountDto();
            userAccountDto.setId(userAccountModel.getId());
            userAccountDto.setCity(userAccountModel.getCity());
            userAccountDto.setLogin(userAccountModel.getLogin());
            userAccountDto.setLogotype(userAccountModel.getLogotype());
            userAccountDto.setPassword(userAccountModel.getPassword());
            userAccountDto.setRoletype(userAccountModel.getRoletype());

            UserAdressModel deliveryAdressModel = userAccountModel.getDeliveryAdress();
            UserAddressDto deliveryAdressDto = new UserAddressDto();
            deliveryAdressDto.setZipcode(deliveryAdressModel.getZipcode());
            deliveryAdressDto.setStreet(deliveryAdressModel.getStreet());
            deliveryAdressDto.setId(deliveryAdressModel.getId());
            deliveryAdressDto.setCountry(deliveryAdressModel.getCountry());
            deliveryAdressDto.setCity(deliveryAdressModel.getCity());
            userAccountDto.setDeliveryAdress(deliveryAdressDto);

            UserAdressModel userAdressModel = userAccountModel.getUserAdress();
            UserAddressDto userAdressDto = new UserAddressDto();
            userAdressDto.setZipcode(userAdressModel.getZipcode());
            userAdressDto.setStreet(userAdressModel.getStreet());
            userAdressDto.setId(userAdressModel.getId());
            userAdressDto.setCountry(userAdressModel.getCountry());
            userAdressDto.setCity(userAdressModel.getCity());
            userAccountDto.setUserAdress(userAdressDto);

            orderDto.setUserAddress(userAdressDto);
            orderDto.setDeliveryAddress(deliveryAdressDto);

            orderDto.setUserAccountDto(userAccountDto);

            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}