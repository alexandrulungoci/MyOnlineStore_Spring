package com.sda.onlinestore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany (mappedBy = "parentCategory")
    private List<CategoryModel> subcategory= new ArrayList<>();

    @ManyToOne
    private CategoryModel parentCategory;

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

    public List<CategoryModel> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<CategoryModel> subcategory) {
        this.subcategory = subcategory;
    }

    public CategoryModel getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryModel parentCategory) {
        this.parentCategory = parentCategory;
    }
}
