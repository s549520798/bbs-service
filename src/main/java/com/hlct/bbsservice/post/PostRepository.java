package com.hlct.bbsservice.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post ,Long>,PagingAndSortingRepository<Post,Long> {

    @Override
    List<Post> findAll();

    List<Post> findPostsByOpenId(String openId);
}
