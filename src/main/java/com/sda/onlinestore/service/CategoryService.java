package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.model.AuthorModel;
import com.sda.onlinestore.model.CategoryModel;
import com.sda.onlinestore.model.ProductModel;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Long id);

    void addCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    AuthorModel getAuthorModelFromService(ProductDto productDto);

    AuthorDto getAuthorFromService(ProductModel productModel);

    List<ProductDto> getProductsByCategory(long id);

}
