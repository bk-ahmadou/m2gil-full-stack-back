package com.example.m2gilfullstackback.controllers;

import com.example.m2gilfullstackback.dtos.ShopGetDto;
import com.example.m2gilfullstackback.dtos.ShopPostDto;
import com.example.m2gilfullstackback.repositories.ShopRepository;
import com.example.m2gilfullstackback.repositories.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/shops")
public class ShopController {
    private MapStructMapper mapStructMapper;
    private ShopRepository shopRepository;

    @Autowired
    public ShopController(MapStructMapper mapStructMapper, ShopRepository shopRepository){
        this.mapStructMapper = mapStructMapper;
        this.shopRepository = shopRepository;
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Void> create(@RequestBody ShopPostDto shopPostDto) {
        shopRepository.save(
                mapStructMapper.shopPostDtoToShop(shopPostDto)
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
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
