package com.hlct.bbsservice.form;

import org.hibernate.annotations.GeneratorType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "form_id")
    private String formId;
    @Column(name = "open_id")
    private String openId;
    @CreatedDate
    @Column(name = "save_date")
    private Date saveDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", formId='" + formId + '\'' +
                ", openId='" + openId + '\'' +
                ", saveDate=" + saveDate +
                '}';
    }
}
