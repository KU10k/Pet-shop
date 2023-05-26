package com.ku10k.petshop.repositories;


import com.ku10k.petshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
}
