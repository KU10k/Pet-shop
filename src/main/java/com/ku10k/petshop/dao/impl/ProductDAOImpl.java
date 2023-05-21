package com.ku10k.petshop.dao.impl;

import com.ku10k.petshop.dao.ProductDAO;
import com.ku10k.petshop.models.Product;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {
    private final SessionFactory sessionFactory;

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Product> products = session.createQuery("from Product", Product.class).getResultList();
        return products;
    }

    @Override
    public Product getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        return product;
    }

    @Override
    public Product createOrUpdate(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        return product;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getById(id));
    }
}
