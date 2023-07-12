package com.ku10k.petshop.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @JoinColumn(name = "image_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
