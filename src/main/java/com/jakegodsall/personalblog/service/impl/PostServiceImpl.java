package com.jakegodsall.personalblog.service.impl;

import com.jakegodsall.personalblog.entity.Post;
import com.jakegodsall.personalblog.exception.ResourceNotFoundException;
import com.jakegodsall.personalblog.payload.PostDto;
import com.jakegodsall.personalblog.repository.PostRepository;
import com.jakegodsall.personalblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert the Dto to an entity
        Post post = mapToEntity(postDto);
        // save the post to the database
        Post newPost = postRepository.save(post);
        // convert the entity to Dto
        return mapToDTO(newPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public PostDto getSinglePost(long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);

    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        // update entity
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        // save the entity to the database
        postRepository.save(post);

        // map to DTO
        return mapToDTO(post);

    }

    @Override
    public void deletePostById(long id) {
        // get post by id
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        // delete the post frm the database.
        postRepository.delete(post);
    }

    private PostDto mapToDTO(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getContent()
        );
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }
}
