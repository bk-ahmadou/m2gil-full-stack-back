package com.example.m2gilfullstackback.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter @Setter
@Table(name = "Product")
@Entity
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    @Basic
    @Column(nullable = false)
    private String name;

    @Column
    @Basic
    private float price;

    @Column
    @Basic
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sho_id")
    private Shop shop;
}
