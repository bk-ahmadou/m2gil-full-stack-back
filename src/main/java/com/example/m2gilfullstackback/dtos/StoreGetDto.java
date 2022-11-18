package com.example.m2gilfullstackback.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.UUID;

public class StoreGetDto {

    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("holiday")
    private Boolean holiday;

    @JsonProperty("schedules")
    private List<SchedulePostDto> schedules;

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
        return holiday;
    }

    public void setHoliday(Boolean holiday) {
        this.holiday = holiday;
    }

    public List<SchedulePostDto> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<SchedulePostDto> schedules) {
        this.schedules = schedules;
    }
}
