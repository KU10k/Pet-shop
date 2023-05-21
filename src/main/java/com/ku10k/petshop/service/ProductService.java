package com.ku10k.petshop.service;

import com.ku10k.petshop.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getById(int id);

    Product createOrUpdate(Product product);

    void delete(int id);
}
