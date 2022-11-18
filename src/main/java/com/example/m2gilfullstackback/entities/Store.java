package com.example.m2gilfullstackback.entities;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Store")
public class Store {
    @Id
    @GeneratedValue
    private UUID id;

    @Basic
    @Column(length = 255, nullable = false)
    private String name;

    @Column
    @Basic
    private Boolean holiday;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreationDateTime = new Date();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<Product> products = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>(7);

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

    public Date getCreationDateTime() {
        return CreationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        CreationDateTime = creationDateTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
