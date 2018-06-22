package com.hlct.bbsservice.post;

import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class PostAndUser {

    private Long id;

    private String title;           //帖子 标题

    private String content;         //帖子 内容

    private String startProvince;   //出发地 省份

    private String startCity;       //出发地 city

    private String destination;     //目的地

    private String gatherTime;      //集合时间

    private String participatorMax;    //参与者 最多人数

    private String participatorMin;    //参与者 最少人数

    private String beginDate;     //开始时间

    private String endDate;       //活动结束日期

    private String openId;        //发帖人对应的openid

    private String imageUrls;     // 图片url

    private String type;          //活动类型

    private String costType;      //活动经费类型

    private Date postTime;        //发布时间


    private String sessionKey;

    private String nickName;

    private int gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

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

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "PostAndUser{" +
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
                ", postTime=" + postTime +
                ", sessionKey='" + sessionKey + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
