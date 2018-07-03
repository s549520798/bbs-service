package com.hlct.bbsservice.collection;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "open_id")
    private String openId;
    @Column(name = "post_id")
    private Long postId;
    @CreatedDate
    @Column(name = "collection_time")
    private Date collectionTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", postId=" + postId +
                ", collectionTime=" + collectionTime +
                '}';
    }
}
