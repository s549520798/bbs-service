package com.hlct.bbsservice.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;           //帖子 标题
    @Column(name = "content", length = 2000)
    private String content;         //帖子 内容
    @Column(name = "start_province")
    private String startProvince;   //出发地 省份
    @Column(name = "start_city")
    private String startCity;       //出发地 city
    @Column(name = "destination")
    private String destination;     //目的地
    @Column(name = "gather_time")
    private String gatherTime;      //集合时间
    @Column(name = "participator_max")
    private String participatorMax;    //参与者 最多人数
    @Column(name = "participator_min")
    private String participatorMin;    //参与者 最少人数
    @Column(name = "begin_date")
    private String beginDate;     //开始时间
    @Column(name = "end_date")
    private String endDate;       //活动结束日期
    @Column(name = "open_id")
    private String openId;        //发帖人对应的openid
    @Column(name = "image_urls", length = 2000)
    private String imageUrls;     // 图片url
    @Column(name = "type")
    private String type;          //活动类型
    @Column(name = "cost_type")
    private String costType;      //活动经费类型
    @CreatedDate
    @Column(name = "post_time")
    private Date postTime;        //发布时间


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartProvince() {
        return startProvince;
    }

    public void setStartProvince(String startProvince) {
        this.startProvince = startProvince;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(String gatherTime) {
        this.gatherTime = gatherTime;
    }

    public String getParticipatorMax() {
        return participatorMax;
    }

    public void setParticipatorMax(String participatorMax) {
        this.participatorMax = participatorMax;
    }

    public String getParticipatorMin() {
        return participatorMin;
    }

    public void setParticipatorMin(String participatorMin) {
        this.participatorMin = participatorMin;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startProvince='" + startProvince + '\'' +
                ", startCity='" + startCity + '\'' +
                ", destination='" + destination + '\'' +
                ", gatherTime='" + gatherTime + '\'' +
                ", participatorMax='" + participatorMax + '\'' +
                ", participatorMin='" + participatorMin + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", openId='" + openId + '\'' +
                ", imageUrls='" + imageUrls + '\'' +
                ", type='" + type + '\'' +
                ", costType='" + costType + '\'' +
                '}';
    }
}
