package com.hlct.bbsservice.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPostIdOrderByCommentTimeDesc(Long postId);

    List<Comment> findAllByOpenIdOrderByCommentTimeDesc(String openId);
}
