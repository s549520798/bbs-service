package com.hlct.bbsservice.collection;

import com.hlct.bbsservice.post.PostPlus;

import java.util.List;

public interface CollectionService {

    Collection save(Collection collection);

    List<Collection> getCollectionsByOpenId(String openId);

    List<PostPlus> getPostsByOpenId(String openId);
}
