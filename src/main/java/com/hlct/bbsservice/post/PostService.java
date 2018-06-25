package com.hlct.bbsservice.post;


import org.springframework.data.domain.Page;

import java.util.List;

interface PostService {
    /**
     * 根据实体类添加post到数据库
     *
     * @param post 帖子
     */
    Post savePost(Post post);

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

    /**
     * 获取到 所有的post 并且 其对应的openId 也会获取到
     * @return list< 对应的组合类 >
     */
    List<PostAndUser> getAllPostsWithUser();

    List<Post> getPagePosts(int page);
}
