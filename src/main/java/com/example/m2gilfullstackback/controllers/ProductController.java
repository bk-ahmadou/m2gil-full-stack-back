package com.example.m2gilfullstackback.controllers;

import com.example.m2gilfullstackback.dtos.ProductGetDto;
import com.example.m2gilfullstackback.dtos.ProductPostDto;
import com.example.m2gilfullstackback.dtos.ProductWithCategoriesDto;
import com.example.m2gilfullstackback.entities.Category;
import com.example.m2gilfullstackback.entities.Product;
import com.example.m2gilfullstackback.entities.Store;
import com.example.m2gilfullstackback.exceptions.ResourceNotFoundException;
import com.example.m2gilfullstackback.repositories.CategoryRepository;
import com.example.m2gilfullstackback.repositories.MapStructMapper;
import com.example.m2gilfullstackback.repositories.ProductRepository;
import com.example.m2gilfullstackback.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {

    private MapStructMapper mapStructMapper;
    private ProductRepository productRepository;
    private StoreRepository storeRepository;
    private CategoryRepository categoryRepository;
    /*@PersistenceContext
    private EntityManager em;*/

    @Autowired
    public ProductController(
            MapStructMapper mapStructMapper,
            ProductRepository productRepository,
            StoreRepository storeRepository,
            CategoryRepository categoryRepository
    ){
        this.mapStructMapper = mapStructMapper;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/stores/{storeId}/products")
    public ResponseEntity<List<ProductWithCategoriesDto>> getAllShopsProducts(
            @PathVariable UUID storeId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size
    ){
        if(size.equals(0)){
            throw new IllegalArgumentException("Page size must not be less than one, Please give another page size");
        }

        if(!storeRepository.existsById(storeId)){
            throw  new ResourceNotFoundException("Shop Not found");
        }

        Pageable pageable = PageRequest.of(page,size);

        List<ProductWithCategoriesDto> products = new ArrayList<>();
        productRepository.findProductsByStoreId(storeId, pageable).forEach(product -> {
            products.add(mapStructMapper.productToProductWithCategoriesDto(product));
        });

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products", params = {"page", "size"}, produces = {"application/json"})
    public ResponseEntity<List<ProductGetDto>> getAllProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size
    ) {
        if(size.equals(0)){
            throw new IllegalArgumentException("Page size must not be less than one, Please give another page size");
        }

        Pageable pageable = PageRequest.of(page,size);

        List<ProductGetDto> products = new ArrayList<>();

        productRepository.findAll(pageable).forEach(product -> {
            products.add(mapStructMapper.productToProductGetDto(product));
        });

        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping(value = "/stores/{storeId}/productsByCategories/{categoryId}", produces = {"application/json"})
    public ResponseEntity<List<ProductGetDto>> getProductsByCategories(
            @PathVariable UUID storeId,
            @PathVariable UUID categoryId
    ){
        List<ProductGetDto> products = new ArrayList<>();

        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not found"));

        Store store = storeRepository.findById(storeId).orElseThrow(()->new ResourceNotFoundException("Store not found"));

        productRepository.findProductsByCategoriesAndStore(category, store).forEach(product -> {
            products.add(mapStructMapper.productToProductGetDto(product));
        });

        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PostMapping(value = "}/products", consumes = {"application/json"})
    public ResponseEntity<Void> createProduct(@RequestBody ProductPostDto productPostDto) {

        Product entity = mapStructMapper.productPostDtoToProduct(productPostDto);
        productRepository.save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping(value = "/stores/{storeId}/products", consumes = {"application/json"})
    public ResponseEntity<Void> associateProductToStore(@PathVariable UUID storeId, @RequestBody ProductPostDto productPostDto){

        Product entity = mapStructMapper.productPostDtoToProduct(productPostDto);

        storeRepository.findById(storeId).map(shop -> {
            entity.setShop(shop);
            return productRepository.save(entity);
        }).orElseThrow(()-> new ResourceNotFoundException("Not found shop by id "+ storeId));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/products/{id}", consumes = {"application/json"})
    public  ResponseEntity<Void> updateProduct(@PathVariable UUID id, @RequestBody ProductGetDto productGetDto){

        if(!Objects.equals(id, productGetDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }

        Product product = productRepository.findById(id).get();

        mapStructMapper.updateProductFromDto(productGetDto,product);

        productRepository.save(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable UUID id){

        /*Product product = em.find(Product.class, id);
        if(product.getCategories() != null){
            product.getCategories().forEach(category -> {
                product.removeCategory(category.getId());
            });
            em.remove(product);
        }*/

        Product product = productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("product does not exist"));

        product.getCategories().removeAll(product.getCategories());

        productRepository.delete(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
