package com.evg.photosharing.service.impl;

import com.evg.photosharing.model.Post;
import com.evg.photosharing.repository.PostRepository;
import com.evg.photosharing.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Value("${upload.path}")
    private String uploadPath;
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPostByAuthorId(String authorId) {
        return postRepository.findAllByAuthorId(authorId);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getById(String id) {
        return postRepository.findById(id);
    }

    @Override
    public Optional<Post> addPost(MultipartFile multipartFile, Post post) {
        String imgName = saveImg(multipartFile);
        if (!imgName.isEmpty()) {
            post.setImgName(imgName);
            return Optional.of(postRepository.insert(post));
        }
        return Optional.empty();
    }

    private String saveImg(MultipartFile file) {
        String resultFileName = "";
        if (file.getContentType().equals("image/jpeg")) {
            File uploadDir = new File(uploadPath);

            if (uploadDir.exists()) {
                uploadDir.mkdir();
            }

            resultFileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        return resultFileName;
    }

    @Override
    public boolean updatePost(MultipartFile multipartFile, Post updatePost) {
        Optional<Post> byId = getById(updatePost.getId());

        if (byId.isPresent()) {
            if (!multipartFile.isEmpty()) {
                byId.get().setImgName(saveImg(multipartFile));
            }
            byId.get().setContent(updatePost.getContent());
            postRepository.save(byId.get());
            return true;
        }

        return false;
    }
}
