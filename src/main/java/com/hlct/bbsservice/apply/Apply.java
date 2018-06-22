package com.hlct.bbsservice.apply;

import javax.persistence.*;

@Entity
@Table(name = "apply")
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "post_id")
    private Long postId; // 对应的post id
    @Column(name = "open_id")
    private String openId; //对应申请人的openId
    @Column(name = "phone_number")
    private String phoneNumber; //申请人的手记号码
    @Column(name = "ec_name")
    private String ecName; // emergency contact name 紧急联系人姓名
    @Column(name = "ec_phone")
    private String ecPhone; // 紧急联系人电话

    private String message; //留言
    @Column(name = "review_status")
    private String reviewStatus; // 审核状态

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getEcPhone() {
        return ecPhone;
    }

    public void setEcPhone(String ecPhone) {
        this.ecPhone = ecPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", postId=" + postId +
                ", openId='" + openId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", ecName='" + ecName + '\'' +
                ", ecPhone='" + ecPhone + '\'' +
                ", message='" + message + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                '}';
    }
}
