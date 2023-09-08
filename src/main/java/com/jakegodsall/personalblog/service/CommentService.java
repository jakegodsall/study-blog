package com.jakegodsall.personalblog.service;

import com.jakegodsall.personalblog.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
