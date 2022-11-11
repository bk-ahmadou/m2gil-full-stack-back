package com.example.m2gilfullstackback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter @Setter
public class ShopGetDto {

    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("isHoliday")
    private Boolean isHoliday;

    @NotNull
    @JsonProperty("openingTime")
    private Date openingTime;

    @NotNull
    @JsonProperty("closingTime")
    private Date closingTime;
}
