package com.example.m2gilfullstackback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

public class StorePostDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("isHoliday")
    private Boolean isHoliday;
    @JsonProperty("schedules")
    Set<SchedulePostDto> schedules = new HashSet<>(7);

    public Set<SchedulePostDto> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<SchedulePostDto> schedules) {
        this.schedules = schedules;
    }

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

}
