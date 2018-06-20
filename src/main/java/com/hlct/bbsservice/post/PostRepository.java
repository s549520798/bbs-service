package com.hlct.bbsservice.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post ,Long> {

    @Override
    List<Post> findAll();

    List<Post> findPostsByOpenId(String openId);
}
