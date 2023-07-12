package com.ku10k.petshop.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @Column(name = "id", updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "original_file_name")
    private String originalFileName;
    @Column(name = "size")
    private Long size;
    @Column(name = "content_type")
    private String contentType;
    @Lob

    @Column(name = "bytes")
    private byte[] bytes;



}
