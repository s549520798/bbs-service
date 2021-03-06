package com.hlct.bbsservice.apply;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
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
    @Column(name = "name")
    private String name; //申请人姓名
    @Column(name = "phone_number")
    private String phoneNumber; //申请人的手记号码
    @Column(name = "ec_name")
    private String ecName; // emergency contact name 紧急联系人姓名
    @Column(name = "ec_phone")
    private String ecPhone; // 紧急联系人电话
    @Column(name = "message",length = 1000)
    private String message; //留言
    @Column(name = "has_confirm",nullable = false)
    private int hasConfirm = 0;            //0标记没有审核，1标记审核过了
    @Column(name = "review_status")
    private String reviewStatus; // 审核状态: 未审核 ， 通过 拒绝
    @CreatedDate
    @Column(name = "apply_time")
    private Date applyTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public int getHasConfirm() {
        return hasConfirm;
    }

    public void setHasConfirm(int hasConfirm) {
        this.hasConfirm = hasConfirm;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", postId=" + postId +
                ", openId='" + openId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", ecName='" + ecName + '\'' +
                ", ecPhone='" + ecPhone + '\'' +
                ", message='" + message + '\'' +
                ", hasConfirm=" + hasConfirm +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", applyTime=" + applyTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apply apply = (Apply) o;

        if (postId != null ? !postId.equals(apply.postId) : apply.postId != null) return false;
        return openId != null ? openId.equals(apply.openId) : apply.openId == null;
    }

    @Override
    public int hashCode() {
        int result = postId != null ? postId.hashCode() : 0;
        result = 31 * result + (openId != null ? openId.hashCode() : 0);
        return result;
    }
}
