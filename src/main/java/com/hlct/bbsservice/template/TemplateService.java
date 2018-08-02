package com.hlct.bbsservice.template;

public interface TemplateService {
    /**
     * @param template 模板：包含 formId 表单Id,从小程序端获取   openId 用户openId,post 的发布者..
     * @return 是否成功
     */
    boolean applyNotify(Template template);
}
