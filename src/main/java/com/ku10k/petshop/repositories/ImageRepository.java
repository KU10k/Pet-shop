package com.ku10k.petshop.repositories;

import com.ku10k.petshop.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
