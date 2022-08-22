package com.evg.photosharing.controller;

import com.evg.photosharing.model.Post;
import com.evg.photosharing.model.dto.GalleryDTO;
import com.evg.photosharing.model.dto.RecommendationDTO;
import com.evg.photosharing.service.PostService;
import com.evg.photosharing.service.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/gallery")
public class GalleryController {

    private final RecommendationService recommendationService;
    private final PostService postService;

    public GalleryController(RecommendationService galleryService, PostService postService) {
        this.recommendationService = galleryService;
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
        return postService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/recommendation")
    public ResponseEntity<List<RecommendationDTO>> getRecommendationUser() {
        List<RecommendationDTO> recommendation = recommendationService.getRecommendationUser();
        return new ResponseEntity<>(recommendation, HttpStatus.OK);
    }
}
