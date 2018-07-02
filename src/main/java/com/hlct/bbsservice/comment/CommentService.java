package com.hlct.bbsservice.comment;

import com.hlct.bbsservice.post.Post;

import java.util.List;

public interface CommentService {
    /**
     * 提交评论到数据库
     * @param comment 评论
     * @return comment
     */
    Comment save(Comment comment);

    /**
     * 根据帖子的id获取其评论
     * @param postId 帖子id
     * @return 评论列表
     */
    List<Comment> getCommentByPostId(Long postId);

    /**
     * 获取用户所评价过的帖子
     * @param openId 用户openId
     * @return 帖子列表
     */
    List<Post> getPostByOpenId(String openId);
}
