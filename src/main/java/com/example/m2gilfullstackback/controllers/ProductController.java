package com.example.m2gilfullstackback.controllers;

import com.example.m2gilfullstackback.dtos.ProductGetDto;
import com.example.m2gilfullstackback.dtos.ProductPostDto;
import com.example.m2gilfullstackback.entities.Product;
import com.example.m2gilfullstackback.exceptions.ResourceNotFoundException;
import com.example.m2gilfullstackback.repositories.MapStructMapper;
import com.example.m2gilfullstackback.repositories.ProductRepository;
import com.example.m2gilfullstackback.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api")
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

    @PostMapping(value = "/shops/{shopId}/products", consumes = {"application/json"})
    public ResponseEntity<Void> createProduct(@PathVariable UUID shopId, @RequestBody ProductPostDto productPostDto){

        Product entity = mapStructMapper.productPostDtoToProduct(productPostDto);

        shopRepository.findById(shopId).map(shop -> {
            entity.setShop(shop);
            return productRepository.save(entity);
        }).orElseThrow(()-> new ResourceNotFoundException("Not found shop by id "+ shopId));

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

}
