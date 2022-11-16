package com.example.m2gilfullstackback.dtos;


import com.example.m2gilfullstackback.entities.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class SchedulePostDto {

    @Temporal(TemporalType.TIME)
    @JsonProperty("opening_time")
    private Date openingTime;

    @JsonProperty("day_of_week")
    private DayOfWeek dayOfWeek;

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
