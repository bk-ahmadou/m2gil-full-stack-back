package com.example.m2gilfullstackback.controllers;

import com.example.m2gilfullstackback.dtos.ProductPostDto;
import com.example.m2gilfullstackback.dtos.ShopGetDto;
import com.example.m2gilfullstackback.dtos.ShopPostDto;
import com.example.m2gilfullstackback.entities.Product;
import com.example.m2gilfullstackback.entities.Shop;
import com.example.m2gilfullstackback.repositories.ProductRepository;
import com.example.m2gilfullstackback.repositories.ShopRepository;
import com.example.m2gilfullstackback.repositories.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/shops")
public class ShopController {
    private MapStructMapper mapStructMapper;
    private ShopRepository shopRepository;

    private ProductRepository productRepository;

    @Autowired
    public ShopController(MapStructMapper mapStructMapper, ShopRepository shopRepository, ProductRepository productRepository){
        this.mapStructMapper = mapStructMapper;
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Void> create(@RequestBody ShopPostDto shopPostDto) {
        Shop shop = mapStructMapper.shopPostDtoToShop(shopPostDto);
        shopRepository.save(shop);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = {"application/json"})
    public  ResponseEntity<Void> updateShop(@PathVariable UUID id, @RequestBody ShopGetDto shopGetDto){

        if(!Objects.equals(id, shopGetDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }

        Shop shop = shopRepository.findById(id).get();

        mapStructMapper.updateShopFromDto(shopGetDto,shop);

        shopRepository.save(shop);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<ShopGetDto>> getAll(){

        List<ShopGetDto> list = new ArrayList<>();

        shopRepository.findAll().forEach(shop -> list.add(mapStructMapper.shopToShopGetDto(shop)));

        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ShopGetDto> getById(
            @PathVariable(value = "id") UUID id
    ) {
        return new ResponseEntity<>(
                mapStructMapper.shopToShopGetDto(
                        shopRepository.findById(id).get()
                ),
                HttpStatus.OK
        );
    }
}
