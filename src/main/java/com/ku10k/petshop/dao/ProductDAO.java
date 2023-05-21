package com.ku10k.petshop.dao;

import com.ku10k.petshop.models.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAll();

    Product getById(int id);

    Product createOrUpdate(Product product);

    void delete(int id);
}
