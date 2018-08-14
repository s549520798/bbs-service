package com.hlct.bbsservice;


import com.hlct.bbsservice.form.Form;
import com.hlct.bbsservice.form.FormServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FormIdTest {

    @Autowired
    FormServiceImpl formService;

    @Test
    @Rollback
    public void testFormId(){
        Form form = new Form("formId0009","openId0001");
        Form form2 = new Form("formId0010","openId0001");
        formService.saveFormId(form);
        formService.saveFormId(form2);
        String validFormId = formService.getValidFormId("openId0001").getFormId();
        Assert.assertEquals("formId0002",validFormId);

    }
}
