package com.example.m2gilfullstackback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class ProductGetDto {
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("price")
    private float price;

    @JsonProperty("description")
    private String description;
}
