package com.example.m2gilfullstackback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

public class ShopPostDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("isHoliday")
    private Boolean isHoliday;

    @JsonProperty("openingTime")
    @Temporal(TemporalType.TIME)
    private Date openingTime;

    @Temporal(TemporalType.TIME)
    @JsonProperty("closingTime")
    private Date closingTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHoliday() {
        return isHoliday;
    }

    public void setHoliday(Boolean holiday) {
        isHoliday = holiday;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }
}
