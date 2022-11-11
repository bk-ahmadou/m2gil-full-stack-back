package com.example.m2gilfullstackback.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Table(name = "Product")
@Entity
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    @Basic
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Basic
    private Float price;

    @Column
    @Basic
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sho_id")
    private Shop shop;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
