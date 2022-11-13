package com.example.m2gilfullstackback.controllers;

import com.example.m2gilfullstackback.dtos.CategoryGetDto;
import com.example.m2gilfullstackback.dtos.CategoryPostDto;
import com.example.m2gilfullstackback.entities.Category;
import com.example.m2gilfullstackback.entities.Product;
import com.example.m2gilfullstackback.exceptions.ResourceNotFoundException;
import com.example.m2gilfullstackback.repositories.CategoryRepository;
import com.example.m2gilfullstackback.repositories.MapStructMapper;
import com.example.m2gilfullstackback.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){

        List<Category> categories = new ArrayList<>();

        categoryRepository.findAll().forEach(categories::add);

        if(categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable UUID id){

        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category does not exist"));

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/categories")
    public ResponseEntity<List<Category>> getAllProductCategories(@PathVariable UUID productId){

        if(!categoryRepository.existsById(productId)){
            throw  new ResourceNotFoundException("Not found product");
        }

        List<Category> categories = categoryRepository.findCategoriesByProductsId(productId);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody CategoryGetDto categoryGetDto){

        if(!Objects.equals(id, categoryGetDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }

        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category does not exist"));

        mapStructMapper.updateCategoryFromDto(categoryGetDto,category);

        return new ResponseEntity<>(categoryRepository.save(category),HttpStatus.OK);
    }

    @PostMapping(value = "/categories", consumes = {"application/json"})
    public ResponseEntity<Void> create(@RequestBody CategoryPostDto categoryPostDto) {
        Category category = mapStructMapper.categoryPostDtoToCategory(categoryPostDto);
        categoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/product/{productId}/categories", consumes = {"application/json"})
    public ResponseEntity<Void> associateCategoryToProduct(@PathVariable UUID productId, @RequestBody CategoryGetDto categoryGetDto){

        Category category = productRepository.findById(productId).map(product -> {
            UUID categoryId = categoryGetDto.getId();

            //Category Exist
            if(!Objects.equals(categoryId, null)){
                Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category does not exist"));
                product.addCategory(existingCategory);
                productRepository.save(product);
                return existingCategory;
            }

            //add new category
            Category categoryToAdd = mapStructMapper.categoryGetDtoToCategory(categoryGetDto);
            product.getCategories().add(categoryToAdd);
            return categoryRepository.save(categoryToAdd);
        }).orElseThrow(()->new ResourceNotFoundException("product does not exist"));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/product/{productId}/categories/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategoryFromProduct(@PathVariable UUID productId, @PathVariable UUID categoryId){

        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("product does not exist"));

        product.removeCategory(categoryId);
        productRepository.save(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable UUID id){

        categoryRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
