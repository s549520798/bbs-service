package com.hlct.bbsservice.collection;

import com.hlct.bbsservice.post.PostPlus;

import java.util.List;

public interface CollectionService {
    /**
     * 添加收藏
     *
     * @param collection 收藏
     * @return 实体类
     */
    Collection save(Collection collection);

    List<Collection> getCollectionsByOpenId(String openId);

    /**
     * 获取用户收藏列表
     *
     * @param openId 用户id
     * @return 帖子list
     */
    List<PostPlus> getPostsByOpenId(String openId);

    /**
     * 判断用户是否收藏过该post
     *
     * @param postId 帖子id
     * @param openId 用户id
     * @return boolean
     */
    boolean isAlreadyCollected(Long postId, String openId);

    /**
     * 用户取消收藏
     * @param postId 帖子id
     * @param openId 用户id
     */
    boolean deleteCollected(Long postId, String openId);
}
