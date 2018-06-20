package com.hlct.bbsservice.post;


import java.util.List;

interface PostService {
    /**
     * 根据实体类添加post到数据库
     *
     * @param post 帖子
     */
    void savePost(Post post);

    /**
     * 根据id 获取post
     *
     * @param id id
     * @return post
     */
    Post getPostById(Long id);

    /**
     * 获取所有post
     *
     * @return list
     */
    List<Post> getAll();

    /**
     * 通过openid 获取 帖子
     * @param openId 用户 openid
     * @return list
     */
    List<Post> getPostsByOpenId(String openId);

}
