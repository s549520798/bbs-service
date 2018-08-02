package com.hlct.bbsservice.apply;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlct.bbsservice.common.ResultInfo;
import com.hlct.bbsservice.post.PostPlus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/apply")
public class ApplyController {

    private ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(ApplyController.class);
    private final ApplyServiceImpl applyService;

    @Autowired
    public ApplyController(ObjectMapper objectMapper, ApplyServiceImpl applyService) {
        this.objectMapper = objectMapper;
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
            resultInfo.setMessage("是发帖人或者已经申请过");
        }
        return resultInfo;
    }

    @PostMapping(value = "/saveAndNotify")
    public ResultInfo<Apply> saveAndNotify(HttpServletRequest request) {
        ResultInfo<Apply> resultInfo = new ResultInfo<>();
        //log.info(apply.toString() + " " + formId);
        String p1 = request.getParameter("apply");
        String formId = request.getParameter("formId");
        try {
            Apply apply = objectMapper.readValue(p1,Apply.class);
            Apply apply1 = applyService.save(apply);
            if (apply1 != null) {
                boolean hasNotified = applyService.notifyAuthor(formId, apply1);
                if (hasNotified) {
                    resultInfo.setMessage("申请并成功通知");
                } else {
                    resultInfo.setMessage("申请成功但通知失败");
                }
                resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
                resultInfo.setData(apply1);
            } else {
                resultInfo.setCode(ResultInfo.RESULT_ERROR);
                resultInfo.setMessage("申请失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("apply 错误");
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
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("获取列表失败");
        }
        return resultInfo;
    }

    @GetMapping(value = "/{postId}/count")
    public ResultInfo<Integer> getApplyCount(@PathVariable long postId) {
        ResultInfo<Integer> resultInfo = new ResultInfo<>();
        resultInfo.setData(applyService.countByPostId(postId));
        resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
        resultInfo.setMessage("请求成功");
        return resultInfo;
    }

    @GetMapping(value = "/{openId}/applyPosts")
    public ResultInfo<List<PostPlus>> getApplyPosts(@PathVariable String openId) {
        ResultInfo<List<PostPlus>> resultInfo = new ResultInfo<>();
        List<PostPlus> list = applyService.findApplyPostsByOpenId(openId);
        if (list != null && list.size() > 0) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("请求成功");
            resultInfo.setData(list);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("列表为空");
        }
        return resultInfo;
    }

    @GetMapping(value = "/{postId}/applyPerson")
    public ResultInfo<List<ApplyUser>> getApplyPerson(@PathVariable long postId) {
        ResultInfo<List<ApplyUser>> resultInfo = new ResultInfo<>();
        List<ApplyUser> wxUsers = applyService.findApplyPersonByPostId(postId);
        if (wxUsers.size() != 0) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("获取成功");
            resultInfo.setData(wxUsers);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("数据为空");
        }
        return resultInfo;
    }

    @PostMapping(value = "/{postId}/hasApplied")
    public ResultInfo getIsApplied(@RequestParam String openId, @PathVariable long postId) {
        ResultInfo resultInfo = new ResultInfo();
        boolean hasApplied = applyService.hasApplied(postId, openId);
        if (hasApplied) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("已经申请过了");
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("还没申请过");
        }
        return resultInfo;
    }

    @GetMapping(value = "/{postId}/getApplicant")
    public ResultInfo<List<ApplyUser>> getApplicant(@PathVariable long postId,
                                                    @RequestParam(name = "openId", required = true) String openId) {
        log.info("applicant 接收到的openId = " + openId);
        ResultInfo<List<ApplyUser>> resultInfo = new ResultInfo<>();
        List<ApplyUser> list = applyService.getApplicants(postId, openId);
        if (list == null) {
            //表示不是post的发布者
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("不是发布者，或者没有申请者");
            resultInfo.setData(null);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("获取列表成功");
            resultInfo.setData(list);
        }
        return resultInfo;
    }

    @GetMapping(value = "/{postId}/getApplicants")
    public ResultInfo<List<ApplyUser>> getApplicant(@PathVariable long postId) {
        ResultInfo<List<ApplyUser>> resultInfo = new ResultInfo<>();
        List<ApplyUser> list = applyService.getApplicants(postId);
        if (list == null) {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("没有申请者");
            resultInfo.setData(null);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("获取列表成功");
            resultInfo.setData(list);
        }
        return resultInfo;
    }

    @PostMapping(value = "/{applyId}/updateStatus")
    public ResultInfo<Apply> updateStatus(@PathVariable long applyId,
                                          @RequestParam(name = "openId") String openId,
                                          @RequestParam(name = "status", defaultValue = "修改") String status) {
        ResultInfo<Apply> resultInfo = new ResultInfo<>();
        int index = applyService.updateStatusByOpenIdAndApplyId(openId, applyId, status);
        if (index < 0) {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("不是发帖人");
        } else if (index == 1) {
            Apply apply = applyService.findOne(applyId);
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("更新成功");
            resultInfo.setData(apply);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("更新失败");
        }
        return resultInfo;
    }

}
