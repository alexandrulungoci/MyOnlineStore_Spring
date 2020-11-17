package com.sda.onlinestore.repository;

import com.sda.onlinestore.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long> {

    CartModel findByUserAccountModel_Id(Long id);

}
