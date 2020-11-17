package com.sda.onlinestore.dto;

import com.sda.onlinestore.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    private long id;
    private String name;
    private List<CategoryDto> subcategory = new ArrayList<>();
    private CategoryDto parentCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryDto> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<CategoryDto> subcategory) {
        this.subcategory = subcategory;
    }

    public CategoryDto getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryDto parentCategory) {
        this.parentCategory = parentCategory;
    }

}

