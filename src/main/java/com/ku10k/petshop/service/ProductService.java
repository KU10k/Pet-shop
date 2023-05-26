package com.ku10k.petshop.service;

import com.ku10k.petshop.models.Product;
import com.ku10k.petshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        log.info("get all products");
        return productRepository.findAll();
    }

    public void save(Product product) {
        log.info("save product {}", product);
        productRepository.save(product);
    }


}



