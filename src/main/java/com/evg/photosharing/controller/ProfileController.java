package com.evg.photosharing.controller;

import com.evg.photosharing.exception.EntityNotFoundException;
import com.evg.photosharing.model.Post;
import com.evg.photosharing.model.dto.ProfileDTO;
import com.evg.photosharing.service.PostService;
import com.evg.photosharing.service.ProfileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final PostService postService;

    @Value("${upload.path}")
    private String uploadPath;

    public ProfileController(ProfileService profileService, PostService postService) {
        this.profileService = profileService;
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable String id) {
        Optional<ProfileDTO> profileById = profileService.getProfileById(id);
        return profileById.isPresent() ? new ResponseEntity<>(profileById.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/post")
    public ResponseEntity<?> updatePost(@RequestParam("file") MultipartFile file,
                                        @Valid Post post) throws EntityNotFoundException {

        return postService.updatePost(file, post) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PostMapping("/add/post")
    public ResponseEntity<Post> addPost(@RequestParam("file") MultipartFile file, @Valid Post post) {
        Optional<Post> postResult = postService.addPost(file, post);
        return postResult.isPresent() ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/image/", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> test() throws IOException {
        URL url = new URL("https://altaitop.ru/wp-content/uploads/2021/02/6E9DuB1Nxg.jpg");
        byte[] bytes;
        try (InputStream inputStream = url.openStream()) {
            bytes = inputStream.readAllBytes();
        }
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }
}
