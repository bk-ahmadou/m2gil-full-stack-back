package com.example.m2gilfullstackback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter @Setter
public class ShopPostDto {
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
