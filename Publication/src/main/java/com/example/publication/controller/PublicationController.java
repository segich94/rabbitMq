package com.example.publication.controller;

import com.example.publication.model.Publication;
import com.example.publication.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/publication")
@RequiredArgsConstructor
public class PublicationController {

    private PublicationService publicationService;

    public Publication newPublication(@RequestBody Publication publication){
        return publicationService.createPublication(publication);
    }
}
