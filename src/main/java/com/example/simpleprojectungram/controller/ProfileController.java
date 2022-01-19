package com.example.simpleprojectungram.controller;

import com.example.simpleprojectungram.exception.NoEntityException;
import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.dto.ProfileDTO;
import com.example.simpleprojectungram.service.PostService;
import com.example.simpleprojectungram.service.impl.ProfileServiceImpl;
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

    private final ProfileServiceImpl profileService;
    private final PostService postService;

    @Value("${upload.path}")
    private String uploadPath;

    public ProfileController(ProfileServiceImpl profileService, PostService postService) {
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
                                        @Valid Post post) throws NoEntityException {

        return postService.updatePost(file, post) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PostMapping("/add/post")
    public ResponseEntity<Post> addPost(@RequestParam("file") MultipartFile file, @Valid Post post) throws IOException {
        Optional<Post> postResult = postService.addPost(file, post);
        return postResult.isPresent() ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/image/", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> test() throws IOException {
//        Resource image = profileService.loadAsResource(imageName);
        URL url = new URL("https://altaitop.ru/wp-content/uploads/2021/02/6E9DuB1Nxg.jpg");
        InputStream inputStream = url.openStream();
        byte[] bytes = inputStream.readAllBytes();
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }
}
