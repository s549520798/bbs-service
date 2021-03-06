package com.hlct.bbsservice.post;


import com.hlct.bbsservice.common.ResultPage;
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
     * 根据 int 页数获取post page
     * @param page  int page
     * @param pageCount 每页容纳数量
     * @return post page
     */
    Page<Post> getPostPage(int page,int pageCount);

    /**
     * 获取帖子列表
     * @param page int page
     * @return list
     */
    List<PostPlus> getPagePosts(int page);

    ResultPage<PostPlus> getPostInPage(int page);

    List<Post> getAllByOpenId(String openId);

    /**
     * 根据帖子Id 获取 帖子信息和 发布者信息
     * @param postId postId
     * @return postPlus
     */
    PostPlus getPostAndAuthor(long postId);
}
