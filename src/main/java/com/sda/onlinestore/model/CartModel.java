package com.sda.onlinestore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineModel> orderLineModelList = new ArrayList<>();

    @OneToOne(mappedBy = "cart")
    private UserAccountModel userAccountModel;

    private Double totalCost;

    public List<OrderLineModel> getOrderLineModelList() {
        return orderLineModelList;
    }

    public void setOrderLineModelList(List<OrderLineModel> orderLineModelList) {
        this.orderLineModelList = orderLineModelList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccountModel getUserAccountModel() {
        return userAccountModel;
    }

    public void setUserAccountModel(UserAccountModel userAccountModel) {
        this.userAccountModel = userAccountModel;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

}
