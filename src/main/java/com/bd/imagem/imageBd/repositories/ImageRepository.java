package com.bd.imagem.imageBd.repositories;

import com.bd.imagem.imageBd.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
