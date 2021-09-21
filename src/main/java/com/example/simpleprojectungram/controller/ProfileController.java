package com.example.simpleprojectungram.controller;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.dto.ProfileDTO;
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

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileServiceImpl profileService;

    @Value("${upload.path}")
    private String uploadPath;

    public ProfileController(ProfileServiceImpl profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable String id) {
        ProfileDTO profile = profileService.getById(id);

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PutMapping("/update/post")
    public ResponseEntity<Post> updatePost(@RequestParam("file") MultipartFile file,
                                           @Valid Post post){
        if(file.isEmpty()){
            Post result = profileService.updatePost(post);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            Post uploadPostWithImg = profileService.uploadFile(file, post);
            Post result = profileService.updatePost(uploadPostWithImg);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }


    }


    @PostMapping("/add/post")
    public ResponseEntity<Post> addPost(@RequestParam("file") MultipartFile file, @Valid Post post) throws IOException {
        Post fileUpload = profileService.uploadFile(file, post);

        if (fileUpload != null) {
            Post newPost = profileService.addPost(post);
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
