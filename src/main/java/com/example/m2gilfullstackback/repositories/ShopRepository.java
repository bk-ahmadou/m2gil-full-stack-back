package com.example.m2gilfullstackback.repositories;

import com.example.m2gilfullstackback.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShopRepository extends JpaRepository<Shop, UUID> {
    //Optional<Shop> findById(UUID id);
}
