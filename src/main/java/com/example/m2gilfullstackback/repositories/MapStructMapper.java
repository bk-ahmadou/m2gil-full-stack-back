package com.example.m2gilfullstackback.repositories;

import com.example.m2gilfullstackback.dtos.ProductGetDto;
import com.example.m2gilfullstackback.dtos.ProductPostDto;
import com.example.m2gilfullstackback.dtos.ShopGetDto;
import com.example.m2gilfullstackback.dtos.ShopPostDto;
import com.example.m2gilfullstackback.entities.Product;
import com.example.m2gilfullstackback.entities.Shop;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper(
        componentModel = "spring"
)
@Repository
public interface MapStructMapper {
    ShopGetDto shopToShopGetDto(Shop shop);
    Shop shopPostDtoToShop(ShopPostDto shopPostDto);

    ProductGetDto productToProductGetDto(Product product);
    Product productPostDtoToProduct(ProductPostDto productPostDto);
}
