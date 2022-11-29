package com.example.m2gilfullstackback.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Schedules")
public class Schedule {
    @Id
    @GeneratedValue
    private UUID id;

    @Basic
    @Temporal(TemporalType.TIME)
    @Column(name = "opening_time", nullable = false)
    private Date openingTime;

    @Basic
    @Temporal(TemporalType.TIME)
    @Column(name = "closing_time", nullable = false)
    private Date closingTime;

    @Basic
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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

    public Store getShop() {
        return store;
    }

    public void setShop(Store store) {
        this.store = store;
    }
}
