package com.example.simpleprojectungram.controller;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.dto.RecommendationDTO;
import com.example.simpleprojectungram.service.GalleryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/gallery")
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping
    private ResponseEntity<List<Post>> getAllPost(@RequestParam(defaultValue = "10") int count,
                                                  @RequestParam(defaultValue = "0") int start)
    {
        List<Post> postList = galleryService.findAllPosts();
        if(postList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Post> collect = postList.stream()
                .skip(start)
                .limit(count)
                .collect(Collectors.toList());

        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    @GetMapping("/user/recommendation")
    private ResponseEntity<List<RecommendationDTO>> getRecommendationUser(){
        List<RecommendationDTO> recommendation = galleryService.getRecommendation();
        return new ResponseEntity<>(recommendation, HttpStatus.OK);
    }
}
