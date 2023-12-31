package com.jakegodsall.personalblog.controller;

import com.jakegodsall.personalblog.payload.posts.PostDto;
import com.jakegodsall.personalblog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        List<PostDto> posts = postService.getAllPosts(pageNo, pageSize);
        return ResponseEntity.ok(posts);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable long id) {
        PostDto singlePost = postService.getSinglePost(id);
        return ResponseEntity.ok(singlePost);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        PostDto postResponse = postService.createPost(postDto);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path="/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return ResponseEntity.ok(postResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

}
