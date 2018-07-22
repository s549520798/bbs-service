package com.hlct.bbsservice.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    List<Apply> findAllByPostId(Long postId);

    int countByPostId(Long postId);

    List<Apply> findAllByOpenId(String openId);

    boolean existsByPostIdAndOpenId(Long postId, String openId);

    @Modifying
    @Query("update Apply apply set apply.reviewStatus = :status ,apply.hasConfirm = :hasConfirm where apply.id = :applyId")
    int updateStatus(@Param("applyId") long applyId,
                     @Param("status") String status,
                     @Param("hasConfirm") int hasConfirm);
}
