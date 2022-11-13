package com.example.m2gilfullstackback.controllers;

import com.example.m2gilfullstackback.dtos.CategoryGetDto;
import com.example.m2gilfullstackback.dtos.CategoryPostDto;
import com.example.m2gilfullstackback.entities.Category;
import com.example.m2gilfullstackback.repositories.CategoryRepository;
import com.example.m2gilfullstackback.repositories.MapStructMapper;
import com.example.m2gilfullstackback.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class CategoryController {

    private MapStructMapper mapStructMapper;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(MapStructMapper mapStructMapper, ProductRepository productRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.mapStructMapper = mapStructMapper;
    }

    @PostMapping(value = "/category", consumes = {"application/json"})
    public ResponseEntity<Void> create(@RequestBody CategoryPostDto categoryPostDto) {
        Category category = mapStructMapper.categoryPostDtoToCategory(categoryPostDto);
        categoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/product/{productId}/category", consumes = {"application/json"})
    public ResponseEntity<Void> associateCategoryToProduct(@PathVariable UUID productId, @RequestBody CategoryGetDto categoryGetDto){

        Category category = productRepository.findById(productId).map(product -> {
            UUID categoryId = categoryGetDto.getId();

            //Category Exist
            if(!Objects.equals(categoryId, null)){
                //NotFoundException without RuntimeException
                Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category does not exist"));
                product.getCategories().add(existingCategory);
                productRepository.save(product);
                return existingCategory;
            }

            //add new category
            Category categoryToAdd = mapStructMapper.categoryGetDtoToCategory(categoryGetDto);
            product.getCategories().add(categoryToAdd);
            return categoryRepository.save(categoryToAdd);
        }).orElseThrow(()->new RuntimeException("product does not exist"));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
