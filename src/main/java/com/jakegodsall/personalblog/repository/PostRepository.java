package com.jakegodsall.personalblog.repository;

import com.jakegodsall.personalblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
