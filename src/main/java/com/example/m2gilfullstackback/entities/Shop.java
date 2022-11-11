package com.example.m2gilfullstackback.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter @Setter
@Entity
@Table(name = "Shop")
public class Shop {
    @Id
    @GeneratedValue
    private UUID id;

    @Basic
    @Column(nullable = false, length = 255)
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
}
