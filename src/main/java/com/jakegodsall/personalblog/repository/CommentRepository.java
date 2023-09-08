package com.jakegodsall.personalblog.repository;

import com.jakegodsall.personalblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
