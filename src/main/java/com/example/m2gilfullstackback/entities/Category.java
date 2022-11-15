package com.example.m2gilfullstackback.entities;

import javax.persistence.*;
import java.util.*;

@Table(name = "Category")
@Entity
public class Category {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    @Basic
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Product> products = new HashSet<>();

    public void removeProduct(Product product){
        this.products.remove(product);
        product.getCategories().remove(this);
    }

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(HashSet<Product> products) {
        this.products = products;
    }
}
