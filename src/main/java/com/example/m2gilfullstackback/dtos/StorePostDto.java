package com.example.m2gilfullstackback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

public class StorePostDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("holiday")
    private Boolean holiday;

    @JsonProperty("schedules")
    Set<SchedulePostDto> schedules = new HashSet<>(7);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHoliday() {
        return holiday;
    }

    public void setHoliday(Boolean holiday) {
        this.holiday = holiday;
    }

    public Set<SchedulePostDto> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<SchedulePostDto> schedules) {
        this.schedules = schedules;
    }
}
