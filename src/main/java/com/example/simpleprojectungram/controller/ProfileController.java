package com.example.simpleprojectungram.controller;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.dto.ProfileDTO;
import com.example.simpleprojectungram.service.ProfileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Value("${upload.path}")
    private String uploadPath;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable String id) {
        ProfileDTO profile = profileService.getProfile(id);

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping("/add/post")
    public ResponseEntity<Post> addPost(@RequestParam("file") MultipartFile file, @Valid Post post) throws IOException {
        if (file.getContentType().equals("image/jpeg")) {
            File uploadDir = new File(uploadPath);

            if (uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String resultFileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
            post.setImgName(resultFileName);

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            Post newPost = profileService.saveNewPost(post);
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<Resource> serveFile(@PathVariable String imageName){
        Resource image = profileService.loadAsResource(imageName);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
