package com.hlct.bbsservice.post;

import javax.persistence.*;
import javax.validation.groups.ConvertGroup;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //帖子 标题
    @Column(name = "title")
    private String title;
    //帖子 内容
    @Column(name = "content")
    private String content;
    //出发地 省份
    @Column(name = "start_province")
    private String startProvince;
    //出发地 city
    @Column(name = "start_city")
    private String startCity;
    //目的地
    @Column(name = "destination")
    private String destination;
    //集结时间
    @Column(name = "gather_time")
    private String gatherTime;
    //参与者 最多人数
    @Column(name = "participator_max")
    private String participatorMax;
    //参与者 最少人数
    @Column(name = "participator_min")
    private String participatorMin;
    //开始时间
    @Column(name = "begin_date")
    private String beginDate;
    //结束时间
    @Column(name = "end_date")
    private String endDate;
    //openId
    @Column(name = "open_id")
    private String openId;

    @Column(name = "image_urls")
    private String imageUrls;


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
}
