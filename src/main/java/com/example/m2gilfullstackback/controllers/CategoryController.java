package com.example.m2gilfullstackback.controllers;

import com.example.m2gilfullstackback.dtos.*;
import com.example.m2gilfullstackback.entities.*;
import com.example.m2gilfullstackback.exceptions.ResourceNotFoundException;
import com.example.m2gilfullstackback.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private MapStructMapper mapStructMapper;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public CategoryController(MapStructMapper mapStructMapper, ProductRepository productRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.mapStructMapper = mapStructMapper;
    }

    @GetMapping(value = "/categories", params = {"page", "size"}, produces = {"application/json"})
    public ResponseEntity<List<CategoryGetDto>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size
    ) {
        if(size.equals(0)){
            throw new IllegalArgumentException("Page size must not be less than one, Please give another page size");
        }

        Pageable pageable = PageRequest.of(page,size);

        List<CategoryGetDto> categories = new ArrayList<>();

        categoryRepository.findAll(pageable).forEach(category -> {
            categories.add(mapStructMapper.categoryToCategoryGetDto(category));
        });

        if(categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryGetDto> getCategory(@PathVariable UUID id){

        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category does not exist"));

        return new ResponseEntity<>(mapStructMapper.categoryToCategoryGetDto(category), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/categories")
    public ResponseEntity<List<CategoryGetDto>> getAllProductCategories(@PathVariable UUID productId){

        if(!productRepository.existsById(productId)){
            throw  new ResourceNotFoundException("Not found product");
        }

        List<CategoryGetDto> categories = new ArrayList<>();
        categoryRepository.findCategoriesByProductsId(productId).forEach(category -> {
            categories.add(mapStructMapper.categoryToCategoryGetDto(category));
        });

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PatchMapping(value = "/categories/{id}")
    public ResponseEntity<CategoryGetDto> updateCategory(@PathVariable UUID id, @RequestBody CategoryPostDto categoryPostDto){

        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category does not exist"));

        mapStructMapper.updateCategoryFromDto(categoryPostDto,category);

        Category categoryToReturn = categoryRepository.save(category);

        return new ResponseEntity<>(mapStructMapper.categoryToCategoryGetDto(categoryToReturn),HttpStatus.OK);
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

    @DeleteMapping("/products/{productId}/categories/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategoryFromProduct(@PathVariable UUID productId, @PathVariable UUID categoryId){

        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("product does not exist"));

        product.removeCategory(categoryId);
        productRepository.save(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/categories/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable UUID id){

        Category category = em.find(Category.class, id);
        if(category.getProducts() != null){
            category.getProducts().forEach(product -> {
                category.removeProduct(product);
            });
            em.remove(category);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
