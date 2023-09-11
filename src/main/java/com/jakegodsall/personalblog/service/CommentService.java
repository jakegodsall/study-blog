package com.jakegodsall.personalblog.service;

import com.jakegodsall.personalblog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getAllCommentsByPostId(long postId);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto updateComment(CommentDto commentDto, long postId, long commentId);
}
