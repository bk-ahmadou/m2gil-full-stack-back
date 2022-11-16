package com.example.m2gilfullstackback.repositories;

import com.example.m2gilfullstackback.dtos.*;
import com.example.m2gilfullstackback.entities.Category;
import com.example.m2gilfullstackback.entities.Product;
import com.example.m2gilfullstackback.entities.Store;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    //Shop Mapper
    StoreGetDto shopToShopGetDto(Store store);
    Store shopPostDtoToShop(StorePostDto storePostDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShopFromDto(StoreUpdateDto dto, @MappingTarget Store store);

    //Product Mapper
    ProductGetDto productToProductGetDto(Product product);
    Product productPostDtoToProduct(ProductPostDto productPostDto);
    ProductWithCategoriesDto productToProductWithCategoriesDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductGetDto dto, @MappingTarget Product product);

    //Category Mapper

    CategoryGetDto categoryToCategoryGetDto(Category category);
    Category categoryPostDtoToCategory(CategoryPostDto categoryPostDto);
    Category categoryGetDtoToCategory(CategoryGetDto categoryGetDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDto(CategoryPostDto dto, @MappingTarget Category category);
}


