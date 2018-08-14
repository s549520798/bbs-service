package com.hlct.bbsservice.form;

public interface FormService {

    /**
     *  将form id 存到数据库
     * @param form form
     * @return 实体
     */
    Form saveFormId(Form form);

    /**
     * 获取数据库中存放的可用的formId
     *
     * @return form
     */
    Form getValidFormId(String openId);

    /**
     * 删除已经用过的formId
     *
     * @param form formId
     * @return 是否删除成功
     */
    boolean deleteInvalidFormId(Form form);

}
