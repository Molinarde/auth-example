package com.example.simpleprojectungram.controller;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.dto.GalleryDTO;
import com.example.simpleprojectungram.model.dto.RecommendationDTO;
import com.example.simpleprojectungram.service.CommentService;
import com.example.simpleprojectungram.service.PostService;
import com.example.simpleprojectungram.service.impl.RecommendationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/gallery")
public class GalleryController {

    private final RecommendationServiceImpl galleryService;
    private final PostService postService;

    public GalleryController(RecommendationServiceImpl galleryService, PostService postService, CommentService commentService) {
        this.galleryService = galleryService;
        this.postService = postService;
    }

    @GetMapping("/")
    public ResponseEntity<GalleryDTO> getAllPost(@RequestParam(defaultValue = "10") int count,
                                                 @RequestParam(defaultValue = "0") int start) {
        List<Post> postList = postService.getAll();
        if (postList.isEmpty() || start >= postList.size()) {
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
    public ResponseEntity<Post> getPostById(@PathVariable String id) {

        Optional<Post> byId = postService.getById(id);

        return byId.isPresent() ? new ResponseEntity<>(byId.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/recommendation")
    public ResponseEntity<List<RecommendationDTO>> getRecommendationUser() {
        List<RecommendationDTO> recommendation = galleryService.getRecommendationUser();
        return new ResponseEntity<>(recommendation, HttpStatus.OK);
    }
}
