package com.hlct.bbsservice.apply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    List<Apply> findAllByPostId(Long postId);
}
