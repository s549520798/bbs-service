package com.hlct.bbsservice.form;

import com.hlct.bbsservice.common.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/form")
public class FormController {
    private final FormServiceImpl formService;
    private final Logger log = LoggerFactory.getLogger(FormController.class);
    @Autowired
    public FormController(FormServiceImpl formService) {
        this.formService = formService;
    }

    @PostMapping(value = "/{openId}/save")
    public ResultInfo<Form> saveFormId(@PathVariable String openId,@RequestParam String formId){
        ResultInfo<Form> resultInfo = new ResultInfo<>();
        log.info("接收到formId = "+ formId);
        Form form = new Form(formId,openId);
        Form form1 = formService.saveFormId(form);
        if (form1 != null){
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("储存成功");
            resultInfo.setData(form1);
        }else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("储存失败");
        }
        return resultInfo;

    }

}
