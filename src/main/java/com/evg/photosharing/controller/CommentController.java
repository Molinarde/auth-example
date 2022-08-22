package com.evg.photosharing.controller;

import com.evg.photosharing.model.Comment;
import com.evg.photosharing.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody @Valid Comment comment) {
        return commentService.addComment(comment).isPresent() ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable String id) {
        return new ResponseEntity<>(commentService.getCommentByPostId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(String id) {
        Optional<Comment> commentById = commentService.getCommentById(id);
        return commentById.map(comment -> new ResponseEntity<>(comment, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
