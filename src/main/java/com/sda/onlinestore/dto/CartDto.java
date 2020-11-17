package com.sda.onlinestore.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CartDto {

    private Long id;
    private List<OrderLineDto> orderLineDtoList = new ArrayList<>();
    private UserAccountDto userAccountDto;
    private Double totalCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderLineDto> getOrderLineDtoList() {
        return orderLineDtoList;
    }

    public void setOrderLineDtoList(List<OrderLineDto> orderLineDtoList) {
        this.orderLineDtoList = orderLineDtoList;
    }

    public UserAccountDto getUserAccountDto() {
        return userAccountDto;
    }

    public void setUserAccountDto(UserAccountDto userAccountDto) {
        this.userAccountDto = userAccountDto;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
