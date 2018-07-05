package com.hlct.bbsservice.collection;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection,Long> {

    List<Collection> findAllByOpenIdOrderByCollectionTimeDesc(String openId);

    boolean existsByPostIdAndOpenId(Long postId, String openId);

    void deleteByPostIdAndOpenId(Long postId, String openId);


}
