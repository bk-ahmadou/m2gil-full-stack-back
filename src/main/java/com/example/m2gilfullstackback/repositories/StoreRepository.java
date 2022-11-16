package com.example.m2gilfullstackback.repositories;

import com.example.m2gilfullstackback.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {
    //Optional<Shop> findById(UUID id);
}
