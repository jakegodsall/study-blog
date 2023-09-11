package com.jakegodsall.personalblog.service.impl;

import com.jakegodsall.personalblog.entity.Comment;
import com.jakegodsall.personalblog.entity.Post;
import com.jakegodsall.personalblog.exception.BlogAPIException;
import com.jakegodsall.personalblog.exception.ResourceNotFoundException;
import com.jakegodsall.personalblog.payload.CommentDto;
import com.jakegodsall.personalblog.repository.CommentRepository;
import com.jakegodsall.personalblog.repository.PostRepository;
import com.jakegodsall.personalblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        // map CommentDto to Comment entity
        Comment comment = mapToEntity(commentDto);
        // retrieve the post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        // set the post to the comment entity
        comment.setPost(post);
        // save the entity in the database
        Comment savedComment = commentRepository.save(comment);
        // map and return the saved Comment to CommentDto
        return mapToDto(savedComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        // map List<Comment> to List<CommentDto>
        return comments.stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        // retrieve the post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
        // throw exception if comment does not belong to post
        if (!(comment.getPost().getId() == post.getId())) {
            throw new BlogAPIException("Comment does not belong to post");
        }
        // map to DTO and return
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long postId, long commentId) {
        // retrieve the post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
        // throw exception if comment does not belong to post
        if (!(comment.getPost().getId() == post.getId())) {
            throw new BlogAPIException("Comment does not belong to post");
        }
        // set new values to Comment entity
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        // save the Comment to the database
        commentRepository.save(comment);
        // map to DTO and return
        return mapToDto(comment);
    }

    @Override
    public void deletePostById(long postId, long commentId) {
        // retrieve the post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
        // throw exception if comment does not belong to post
        if (!(comment.getPost().getId() == post.getId())) {
            throw new BlogAPIException("Comment does not belong to post");
        }

        commentRepository.deleteById(commentId);
    }

    private Comment mapToEntity(CommentDto commentDto) {
        // map CommentDto data transfer object to Comment entity
        return new Comment(
                commentDto.getId(),
                commentDto.getName(),
                commentDto.getEmail(),
                commentDto.getBody()
        );
    }

    private CommentDto mapToDto(Comment comment) {
        // map Comment entity to CommentDto data transfer object
        return new CommentDto(
                comment.getId(),
                comment.getName(),
                comment.getEmail(),
                comment.getBody()
        );
    }
}
