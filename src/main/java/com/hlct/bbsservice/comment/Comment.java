package com.hlct.bbsservice.comment;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "post_id")
    private Long postId;    //评论对象
    @Column(name = "open_id")
    private String openId;  //评论人openId
    private String comment; //评论内容
    @Column(name = "comment_time")
    private Date commentTime; //评论时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", openId='" + openId + '\'' +
                ", comment='" + comment + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }
}
