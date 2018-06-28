package com.hlct.bbsservice.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post ,Long>,PagingAndSortingRepository<Post,Long> {

    List<Post> findAllByOpenId(String openId);

}
