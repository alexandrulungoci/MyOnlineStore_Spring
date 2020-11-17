package com.sda.onlinestore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private double totalCost;

    @ManyToOne
    private UserAdressModel deliveryAddress;

    @ManyToOne
    private UserAdressModel userAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserAccountModel userAccountModel;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineModel> orderLineModelList = new ArrayList<>();

    @Enumerated
    private OrderStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public UserAdressModel getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(UserAdressModel deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public UserAdressModel getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAdressModel userAddress) {
        this.userAddress = userAddress;
    }

    public UserAccountModel getUserAccountModel() {
        return userAccountModel;
    }

    public void setUserAccountModel(UserAccountModel userAccountModel) {
        this.userAccountModel = userAccountModel;
    }

    public List<OrderLineModel> getOrderLineModelList() {
        return orderLineModelList;
    }

    public void setOrderLineModelList(List<OrderLineModel> orderLineModelList) {
        this.orderLineModelList = orderLineModelList;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
