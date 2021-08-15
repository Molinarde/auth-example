package com.example.simpleprojectungram.controller;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.dto.GalleryDTO;
import com.example.simpleprojectungram.model.dto.RecommendationDTO;
import com.example.simpleprojectungram.service.GalleryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<GalleryDTO> getAllPost(@RequestParam(defaultValue = "10") int count,
                                                  @RequestParam(defaultValue = "0") int start)
    {
        List<Post> postList = galleryService.findAllPosts();
        if(postList.isEmpty() || start >= postList.size()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Post> collect = postList.stream()
                .skip(start)
                .limit(count)
                .collect(Collectors.toList());
        GalleryDTO galleryDTO = new GalleryDTO(collect, postList.size());
        return new ResponseEntity<>(galleryDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Post> getPostById(@PathVariable String id){
        System.out.println(id);
        Post post = galleryService.findPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/user/recommendation")
    private ResponseEntity<List<RecommendationDTO>> getRecommendationUser(){
        List<RecommendationDTO> recommendation = galleryService.getRecommendation();
        return new ResponseEntity<>(recommendation, HttpStatus.OK);
    }
}
