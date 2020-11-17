package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.dto.ProductDto;
import com.sda.onlinestore.model.AuthorModel;
import com.sda.onlinestore.model.CategoryModel;
import com.sda.onlinestore.model.ProductModel;
import com.sda.onlinestore.repository.AuthorRepository;
import com.sda.onlinestore.repository.CategoryRepository;
import com.sda.onlinestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public AuthorDto getAuthorFromService(ProductModel productModel) {
        if (productModel.getAuthor() == null) {
            return null;
        }
            AuthorModel authorModel = productModel.getAuthor();
            AuthorDto authorDto = new AuthorDto();
            if (authorModel == null) {
                return null;
            }

//        authorDto.setId(authorModel.getId());
            authorDto.setFirstName(authorModel.getFirstName());
            authorDto.setLastName(authorModel.getLastName());

            return authorDto;

        }

    public AuthorModel getAuthorModelFromService(ProductDto productDto) {
        AuthorDto authorDto = productDto.getAuthor();
        if (authorDto == null) {
            return null;
        }
        Long idAuthor = authorDto.getId();
        Optional<AuthorModel> authorModelOptional = authorRepository.findById(idAuthor);
        if (authorModelOptional.isPresent()) {
            return authorModelOptional.get();
        }
        return null;
    }

    @Override
    public ProductDto getProduct(Long id) {

        Optional<ProductModel> foundProductModel = productRepository.findById(id);
        if (foundProductModel.isPresent()) {
            ProductModel productModel = foundProductModel.get();
            ProductDto productDto = new ProductDto();

            CategoryModel categoryModel = productModel.getCategory();

            //Every Product has a CAtegory Model which include a parentCategory->Category type
            CategoryDto categoryDto = new CategoryDto();
            if(categoryModel!=null) {
                categoryDto.setId(categoryModel.getId());
                // categoryDto.setParentCategory(categoryModel.getParentCategory());

                categoryDto.setName(categoryModel.getName());
                //categoryDto.setSubcategory(categoryModel.getSubcategory());

                //  categoryDto.setParentCategory(setParentCategory(ProductModel productModel);
                productDto.setCategory(categoryDto);
            }
            productDto.setId(productModel.getId());
            productDto.setPrice(productModel.getPrice());
            productDto.setProductType(productModel.getProductType());
            productDto.setThumbnail(productModel.getThumbnail());
            productDto.setTitle(productModel.getTitle());

            productDto.setAuthor(getAuthorFromService(productModel));
            return productDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        System.out.println("Am intrat in metoda getAllProducts()...");
        List<ProductModel> productModelList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();

        for (ProductModel productModel : productModelList) {
            ProductDto productDto = new ProductDto();
            productDto.setId(productModel.getId());
            productDto.setTitle(productModel.getTitle());
            productDto.setProductType(productModel.getProductType());
            productDto.setThumbnail(productModel.getThumbnail());
            productDto.setPrice(productModel.getPrice());


            CategoryDto categoryDto = new CategoryDto();
            CategoryModel categoryModel = productModel.getCategory();
            if (categoryModel != null) {
                Long idCategory = categoryModel.getId();
                Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(idCategory);
                if (categoryModelOptional.isPresent()) {
                    CategoryModel categoryModel1 = categoryModelOptional.get();

                    categoryDto.setId(categoryModel1.getId());
                    categoryDto.setName(categoryModel1.getName());
                    productDto.setCategory(categoryDto);
                }

                CategoryDto parentDtoCategory = new CategoryDto();
                Long idParentCategory = productModel.getCategory().getId();
                Optional<CategoryModel> categoryParentModelOptional = categoryRepository.findById(idCategory);
//
//
//            if (categoryParentModelOptional.isPresent()) {
//                CategoryModel categoryModel2 = categoryModelOptional.get();
//                parentDtoCategory.setId(categoryModel2.getId());
//                parentDtoCategory.setName(categoryModel2.getName());
//                categoryDto.setParentCategory(parentDtoCategory);
//            }
//
//            productDto.setCategory(categoryDto);
            productDto.setAuthor(getAuthorFromService(productModel));
            }

            productDtoList.add(productDto);
        }

        System.out.println("productDtoList: " + productDtoList);
        return productDtoList;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void addProduct(ProductDto productDto) {
        ProductModel productModel = new ProductModel();
        productModel.setId(productDto.getId());
        //  productModel.setAuthor(getAuthorModelFromService(productDto));
        CategoryDto categoryDto = productDto.getCategory();
        if (categoryDto != null) {

            Long idCategory = categoryDto.getId();
            Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(idCategory);
            if (categoryModelOptional.isPresent()) {
                CategoryModel categoryModel = categoryModelOptional.get();
                productModel.setCategory(categoryModel);
            }

        }

        productModel.setPrice(productDto.getPrice());
        productModel.setProductType(productDto.getProductType());
        productModel.setThumbnail(productDto.getThumbnail());
        productModel.setTitle(productDto.getTitle());

        productModel.setAuthor(getAuthorModelFromService(productDto));

        productRepository.save(productModel);
    }


    @Override
    public void updateProduct(ProductDto productDto) {
        Optional<ProductModel> productModelFind = productRepository.findById(productDto.getId());
        if (productModelFind.isPresent()) {
            ProductModel productModel = productModelFind.get();
//            productModel.setId(productDto.getId());
            productModel.setAuthor(getAuthorModelFromService(productDto));

            long idCategory = productDto.getCategory().getId();
            Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(idCategory);
            if (categoryModelOptional.isPresent()) {
                CategoryModel categoryModel = categoryModelOptional.get();
                productModel.setCategory(categoryModel);
            }

            productModel.setPrice(productDto.getPrice());
            productModel.setProductType(productDto.getProductType());
            productModel.setThumbnail(productDto.getThumbnail());
            productModel.setTitle(productDto.getTitle());

            productRepository.save(productModel);

        }


    }
}
