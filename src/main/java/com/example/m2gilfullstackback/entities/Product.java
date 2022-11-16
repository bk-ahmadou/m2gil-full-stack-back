package com.example.m2gilfullstackback.entities;

import javax.persistence.*;
import java.util.*;

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
    private Store store;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<Category>();

    public void removeCategory(UUID categoryId){
        Category category = this.categories.stream().filter(c ->c.getId().equals(categoryId)).findFirst().orElse(null);
        if(category != null){
            this.categories.remove(category);
            category.getProducts().remove(this);
        }
    }

    public void addCategory(Category category){
        this.categories.add(category);
        category.getProducts().add(this);
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

    public Store getShop() {
        return store;
    }

    public void setShop(Store store) {
        this.store = store;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
