package com.bd.imagem.imageBd.controller;

import com.bd.imagem.imageBd.model.Image;
import com.bd.imagem.imageBd.service.ImageImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageImpl service;

    private final ObjectMapper objectMapper;


    @PostMapping
    public ResponseEntity<Image> save (@RequestBody String jsonString) throws JsonProcessingException {

        Image image = objectMapper.readValue(jsonString, Image.class);

        String base64String = Base64.getEncoder().encodeToString(image.getImage());

        image.setImage(base64String.getBytes());

        Image savedImage = service.save(image);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedImage.getId()).toUri();
        return ResponseEntity.created(location).body(savedImage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Image> findById(@PathVariable Long id) {
        Optional<Image> image = service.findById(id);
        return image.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
