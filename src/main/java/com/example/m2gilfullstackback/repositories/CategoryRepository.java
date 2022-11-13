package com.example.m2gilfullstackback.repositories;

import com.example.m2gilfullstackback.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findCategoriesByProductsId(UUID productId);
}
