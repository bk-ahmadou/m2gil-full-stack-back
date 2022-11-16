package com.example.m2gilfullstackback.dtos;

import com.example.m2gilfullstackback.entities.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProductWithCategoriesDto {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("description")
    private String description;

    @JsonProperty("categories")
    private Set<CategoryGetDto> categories = new HashSet<CategoryGetDto>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CategoryGetDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryGetDto> categories) {
        this.categories = categories;
    }
}
