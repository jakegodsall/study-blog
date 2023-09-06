package com.jakegodsall.personalblog.controller;

import com.jakegodsall.personalblog.payload.PostDto;
import com.jakegodsall.personalblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path="/api/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping(path="/api/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping(path="/api/posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable long id) {
        PostDto singlePost = postService.getSinglePost(id);
        return ResponseEntity.ok(singlePost);
    }
}
