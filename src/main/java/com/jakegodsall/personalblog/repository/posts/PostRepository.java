package com.jakegodsall.personalblog.repository.posts;

import com.jakegodsall.personalblog.entity.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
