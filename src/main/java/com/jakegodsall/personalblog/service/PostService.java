package com.jakegodsall.personalblog.service;

import com.jakegodsall.personalblog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts(int pageNo, int pageSize);
    PostDto getSinglePost(long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePostById(long id);
}
