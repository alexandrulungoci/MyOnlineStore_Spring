package com.sda.onlinestore.dto;

import com.sda.onlinestore.model.OrderStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private long id;
    private String userName;
    private double totalCost;
    private UserAddressDto deliveryAddress;
    private UserAddressDto userAddress;
    private UserAccountDto userAccountDto;
    private Date orderDate;
    private List<OrderLineDto> orderLineDtoList = new ArrayList<>();
    private OrderStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public UserAddressDto getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(UserAddressDto deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public UserAccountDto getUserAccountDto() {
        return userAccountDto;
    }

    public void setUserAccountDto(UserAccountDto userAccountDto) {
        this.userAccountDto = userAccountDto;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderLineDto> getOrderLineDtoList() {
        return orderLineDtoList;
    }

    public void setOrderLineDtoList(List<OrderLineDto> orderLineDtoList) {
        this.orderLineDtoList = orderLineDtoList;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserAddressDto getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressDto userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
