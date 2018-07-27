package com.hlct.bbsservice.template;

public class TemplateParameter {

    public TemplateParameter(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TemplateParameter{" +
                "value='" + value + '\'' +
                '}';
    }
}
