package com.bd.imagem.imageBd.service;

import com.bd.imagem.imageBd.model.Image;
import com.bd.imagem.imageBd.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageImpl {

    private final ImageRepository imageRepository;


    public Image save (Image image) {
        return imageRepository.save(image);
    }

    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }
}
