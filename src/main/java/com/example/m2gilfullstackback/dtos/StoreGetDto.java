package com.example.m2gilfullstackback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.UUID;

public class StoreGetDto {
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

    public Boolean getHoliday() {
        return isHoliday;
    }

    public void setHoliday(Boolean holiday) {
        isHoliday = holiday;
    }
    public List<SchedulePostDto> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<SchedulePostDto> schedules) {
        this.schedules = schedules;
    }

    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("isHoliday")
    private Boolean isHoliday;

    @JsonProperty("schedules")
    private List<SchedulePostDto> schedules;

}
