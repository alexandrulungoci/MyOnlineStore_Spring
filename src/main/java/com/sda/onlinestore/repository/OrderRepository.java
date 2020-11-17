package com.sda.onlinestore.repository;

import com.sda.onlinestore.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    List<OrderModel> findByUserAccountModel_Id(Long userId);

//    List<OrderModel> findByStatusEquals(String status);
}
