package com.hlct.bbsservice.wxuser;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wx_user",uniqueConstraints = {@UniqueConstraint(columnNames = {"open_id"})})
public class WxUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "open_id",nullable = false,unique = true)
    private String openId;
    @Column(name = "session_key")
    private String sessionKey;
    @Column(name = "nick_name")
    private String nickName;
    @Column
    private int gender;
    @Column
    private String language;
    @Column
    private String city;
    @Column
    private String province;
    @Column
    private String country;
    @Column
    private String avatarUrl;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "real_name")
    private String realName;


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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "WxUser{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WxUser wxUser = (WxUser) o;
        return Objects.equals(openId, wxUser.openId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openId);
    }
}
