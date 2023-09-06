package com.jakegodsall.personalblog.service;

import com.jakegodsall.personalblog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getSinglePost(long id);
}
