package com.example.m2gilfullstackback.dtos;

import com.example.m2gilfullstackback.entities.Schedule;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StoreUpdateDto {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("holiday")
    private Boolean holiday;

    @JsonProperty("schedules")
    private List<ScheduleGetDto> schedules = new ArrayList<>(7);

    public List<ScheduleGetDto> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleGetDto> schedules) {
        this.schedules = schedules;
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
}
