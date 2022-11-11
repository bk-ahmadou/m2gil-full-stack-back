package com.example.m2gilfullstackback.controllers;

import com.example.m2gilfullstackback.dtos.ProductPostDto;
import com.example.m2gilfullstackback.entities.Product;
import com.example.m2gilfullstackback.repositories.MapStructMapper;
import com.example.m2gilfullstackback.repositories.ProductRepository;
import com.example.m2gilfullstackback.repositories.ShopRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/shops/{shopId}/products")
public class ProductController {

    private MapStructMapper mapStructMapper;
    private ProductRepository productRepository;
    private ShopRepository shopRepository;

    @Autowired
    public ProductController(MapStructMapper mapStructMapper, ProductRepository productRepository, ShopRepository shopRepository){
        this.mapStructMapper = mapStructMapper;
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Void> createProduct(@PathVariable UUID shopId, @RequestBody ProductPostDto productPostDto){

        Product entity = mapStructMapper.productPostDtoToProduct(productPostDto);

        shopRepository.findById(shopId).map(shop -> {
            entity.setShop(shop);
            return productRepository.save(entity);
        }).orElseThrow(()-> new RuntimeException("Not found shop by id "+ shopId)); // notfoundexception without runtimeexception

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
