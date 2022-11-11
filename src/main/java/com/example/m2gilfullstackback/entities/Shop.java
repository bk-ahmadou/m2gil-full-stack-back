package com.example.m2gilfullstackback.entities;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Shop")
public class Shop {
    @Id
    @GeneratedValue
    private UUID id;

    @Basic
    @Column(length = 255, nullable = false)
    private String name;

    @Column
    @Basic
    private Boolean isHoliday;

    @Basic
    @Temporal(TemporalType.TIME)
    @Column(name = "opening_time", nullable = false)
    private Date openingTime;

    @Basic
    @Column(name = "closing_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date closingTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop")
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
