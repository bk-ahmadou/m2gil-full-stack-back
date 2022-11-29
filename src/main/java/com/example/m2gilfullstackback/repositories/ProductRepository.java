package com.example.m2gilfullstackback.repositories;

import com.example.m2gilfullstackback.entities.Category;
import com.example.m2gilfullstackback.entities.Product;
import com.example.m2gilfullstackback.entities.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findProductsByStoreId(UUID shopId, Pageable pageable);
    List<Product> findProductsByStoreId(UUID shopId);

    List<Product> findProductsByCategoriesAndStore(Category category, Store store);
}
