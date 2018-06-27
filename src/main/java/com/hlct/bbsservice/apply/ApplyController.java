package com.hlct.bbsservice.apply;

import com.hlct.bbsservice.common.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResultInfo<Apply> save(@ModelAttribute Apply apply) {
        ResultInfo<Apply> resultInfo = new ResultInfo<>();
        Apply a = applyService.save(apply);
        if (a != null) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("提交成功");
            resultInfo.setData(a);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("提交失败");
        }
        return resultInfo;
    }

    @GetMapping(value = "/{openId}/applies")
    public ResultInfo<List<Apply>> getApplies(@PathVariable String openId) {
        ResultInfo<List<Apply>> resultInfo = new ResultInfo<>();
        List<Apply> list = applyService.findAllByOpenId(openId);
        if (list != null && list.size() > 0) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("获取列表成功");
            resultInfo.setData(list);
        }else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("获取列表失败");
        }
        return resultInfo;
    }
}
