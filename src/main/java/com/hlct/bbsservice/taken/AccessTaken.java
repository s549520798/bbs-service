package com.hlct.bbsservice.taken;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class AccessTaken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("access_token")
    @Column(name = "access_taken")
    private String AccessTaken;
    @JsonProperty("expires_in")
    @Column(name = "expires_in")
    private long ExpiresIn;
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessTaken() {
        return AccessTaken;
    }

    public void setAccessTaken(String accessTaken) {
        AccessTaken = accessTaken;
    }

    public long getExpiresIn() {
        return ExpiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        ExpiresIn = expiresIn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean usable() {
        Date now = new Date();
        if (now.getTime() - this.getCreateTime().getTime() > 7000 * 1000) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessTaken that = (AccessTaken) o;

        return AccessTaken != null ? AccessTaken.equals(that.AccessTaken) : that.AccessTaken == null;
    }

    @Override
    public int hashCode() {
        return AccessTaken != null ? AccessTaken.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AccessTaken{" +
                "id=" + id +
                ", AccessTaken='" + AccessTaken + '\'' +
                ", ExpiresIn=" + ExpiresIn +
                ", createTime=" + createTime +
                '}';
    }
}
