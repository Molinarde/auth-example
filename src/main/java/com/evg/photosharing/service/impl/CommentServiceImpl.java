package com.evg.photosharing.service.impl;

import com.evg.photosharing.exception.EntityNotFoundException;
import com.evg.photosharing.model.Comment;
import com.evg.photosharing.model.Post;
import com.evg.photosharing.model.User;
import com.evg.photosharing.repository.CommentRepository;
import com.evg.photosharing.service.CommentService;
import com.evg.photosharing.service.PostService;
import com.evg.photosharing.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public Optional<Comment> addComment(Comment comment) {
        Optional<Post> postById = postService.getById(comment.getPostId());
        Optional<User> userById = userService.getById(comment.getAuthorId());
        if (postById.isPresent() && userById.isPresent()) {
            comment.setAuthorName(userById.get().getUsername());
            if (comment.getParentId() == null) {
                return Optional.of(commentRepository.save(comment));
            } else {
                Optional<Comment> parentComment = commentRepository.findById(comment.getParentId());
                if(parentComment.isEmpty())
                    throw new EntityNotFoundException("Comment not found");

                if (parentComment.get().getPostId().equals(comment.getPostId())) {
                    return Optional.of(commentRepository.insert(comment));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void removeComment(String id) {
        Optional<Comment> commentById = commentRepository.findById(id);
        commentById.ifPresent(commentRepository::delete);
    }

    @Override
    public void updateComment(Comment comment) {
        Optional<Comment> commentById = commentRepository.findById(comment.getId());
        commentById.ifPresent(value -> commentRepository.save(comment));
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getCommentByPostId(String id) {
        return commentRepository.findAllByPostId(id);
    }
}
