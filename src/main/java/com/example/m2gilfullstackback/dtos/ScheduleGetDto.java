package com.example.m2gilfullstackback.dtos;

import com.example.m2gilfullstackback.entities.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

public class ScheduleGetDto {
    @JsonProperty("id")
    UUID id;

    @Temporal(TemporalType.TIME)
    @JsonProperty("opening_time")
    private Date openingTime;

    @Temporal(TemporalType.TIME)
    @JsonProperty("closing_time")
    private Date closingTime;

    @JsonProperty("day_of_week")
    private DayOfWeek dayOfWeek;

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
