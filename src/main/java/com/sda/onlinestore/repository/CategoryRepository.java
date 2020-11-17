package com.sda.onlinestore.repository;

import com.sda.onlinestore.model.CategoryModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long>{
}
