package com.hlct.bbsservice.apply;

import com.hlct.bbsservice.common.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/apply")
public class ApplyController {

    private static final Logger log = LoggerFactory.getLogger(ApplyController.class);
    private final ApplyServiceImpl applyService;
    @Autowired
    public ApplyController(ApplyServiceImpl applyService) {
        this.applyService = applyService;
    }

    @PostMapping(value = "/save")
    public ResultInfo<Apply> save(Apply apply){
        Apply a = applyService.save(apply);
        return null;
    }
}
