package com.example.m2gilfullstackback.repositories;

import com.example.m2gilfullstackback.dtos.*;
import com.example.m2gilfullstackback.entities.Category;
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
    //Shop Mapper
    ShopGetDto shopToShopGetDto(Shop shop);
    Shop shopPostDtoToShop(ShopPostDto shopPostDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShopFromDto(ShopGetDto dto, @MappingTarget Shop shop);

    //Product Mapper
    ProductGetDto productToProductGetDto(Product product);
    Product productPostDtoToProduct(ProductPostDto productPostDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductGetDto dto, @MappingTarget Product product);

    //Category Mapper

    CategoryGetDto categoryToCategoryGetDto(Category category);
    Category categoryPostDtoToCategory(CategoryPostDto categoryPostDto);
    Category categoryGetDtoToCategory(CategoryGetDto categoryGetDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDto(CategoryGetDto dto, @MappingTarget Category category);
}


