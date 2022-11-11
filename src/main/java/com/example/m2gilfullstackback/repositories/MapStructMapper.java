package com.example.m2gilfullstackback.repositories;

import com.example.m2gilfullstackback.dtos.ProductGetDto;
import com.example.m2gilfullstackback.dtos.ProductPostDto;
import com.example.m2gilfullstackback.dtos.ShopGetDto;
import com.example.m2gilfullstackback.dtos.ShopPostDto;
import com.example.m2gilfullstackback.entities.Product;
import com.example.m2gilfullstackback.entities.Shop;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Optional;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    ShopGetDto shopToShopGetDto(Shop shop);
    Shop shopPostDtoToShop(ShopPostDto shopPostDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShopFromDto(ShopGetDto dto, @MappingTarget Shop shop);

    ProductGetDto productToProductGetDto(Product product);
    Product productPostDtoToProduct(ProductPostDto productPostDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductGetDto dto, @MappingTarget Product product);
}
