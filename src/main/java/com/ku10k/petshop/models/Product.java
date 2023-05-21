package com.ku10k.petshop.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="products")
@Data
@AllArgsConstructor
public class Product {
    @Id
    @Column(name="id",unique = true ,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="price")
    private int price;
    @Column(name="caption")
    private String caption;
    @Column(name="name_product")
    private String nameProduct;

    public Product() {

    }
}
