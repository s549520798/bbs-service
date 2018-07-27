package com.hlct.bbsservice.template;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Template {

    @JsonProperty("touser")
    private String toUser;
    @JsonProperty("template_id")
    private String templateId;
    private String page;
    @JsonProperty("form_id")
    private String formId;
    @JsonProperty("data")
    private Map<String, TemplateParameter> data;
    @JsonProperty("emphasis_keyword")
    private String emphasisKeyword;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Map<String, TemplateParameter> getData() {
        return data;
    }

    public void setData(Map<String, TemplateParameter> data) {
        this.data = data;
    }

    public String getEmphasisKeyword() {
        return emphasisKeyword;
    }

    public void setEmphasisKeyword(String emphasisKeyword) {
        this.emphasisKeyword = emphasisKeyword;
    }
}
