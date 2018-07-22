package com.hlct.bbsservice.wxuser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlct.bbsservice.common.ResultInfo;
import com.sun.javafx.util.Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@RestController
@RequestMapping(value = "/wxuser")
public class WxUserController {
    private final WxUserRepository repository;
    private final WxUserServiceImpl wxUserService;

    private static final Logger log = LoggerFactory.getLogger(WxUserController.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public WxUserController(WxUserRepository repository, WxUserServiceImpl wxUserService) {
        this.repository = repository;
        this.wxUserService = wxUserService;
    }

    @RequestMapping(value = "/getOpenId", method = RequestMethod.POST)
    public ResultInfo getOpenId(@RequestParam(value = "code") String code) {
        ResultInfo<LoginUser> resultInfo = new ResultInfo<>();
        log.info("收到的 code = " + code);
        WxUser user = wxUserService.getOpenId(code);
        if (user != null) {
            //获取到openId,但是不确定用户是否授权了用户信息
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            if (user.getNickName() != null) {
                //根据用户nickName判断用户已经授权过信息
                log.info("openId 和 用户数据都已经存在");
                resultInfo.setMessage("openId获取成功，已存在数据用户");
                LoginUser loginUser = new LoginUser();
                loginUser.setOldUser(true);
                loginUser.setOpenid(user.getOpenId());
                loginUser.setNickName(user.getNickName());
                loginUser.setCity(user.getCity());
                loginUser.setProvince(user.getProvince());
                loginUser.setGender(user.getGender());
                loginUser.setAvatarUrl(user.getAvatarUrl());
                loginUser.setCountry(user.getCountry());
                loginUser.setLanguage(user.getLanguage());
                resultInfo.setData(loginUser);
            } else {
                //用户没有授权
                resultInfo.setMessage("获取openID成功，无数据新用户");
                LoginUser loginUser = new LoginUser();
                loginUser.setOldUser(false);
                loginUser.setOpenid(user.getOpenId());
                resultInfo.setData(loginUser);
            }
        } else {
            //获取openID失败
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("获取用户信息失败");
        }
        log.info("返回结果：" + resultInfo.toString());
        return resultInfo;
    }

    @GetMapping(value = "/{openId}/info")
    public ResultInfo<WxUser> getInfo(@PathVariable String openId) {
        ResultInfo<WxUser> resultInfo = new ResultInfo<>();
        WxUser wxUser = wxUserService.findByOpenId(openId);
        if (wxUser != null) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("用户信息获取成功");
            resultInfo.setData(wxUser);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("用户信息获取失败");
        }

        return resultInfo;
    }

    @RequestMapping(value = "/postUser", method = RequestMethod.POST)
    public String postUser(@ModelAttribute WxUser user) {
        return "success";
    }

    @RequestMapping(value = "/postUserInfo", method = RequestMethod.POST)
    public ResultInfo<WxUser> userInfo(@Param(value = "openid") String openid, @Param(value = "userInfo") String userInfo) {
        log.info("============提交用户数据======================");
        log.info(openid);
        log.info(userInfo);

        ResultInfo<WxUser> resultInfo = new ResultInfo<>();
        if (openid != null && !openid.isEmpty()) {
            WxUser wxUser = repository.findWxUserByOpenId(openid);
            log.info("根据openid 获取的 wxUser" + wxUser.toString());
            if (userInfo != null && !userInfo.isEmpty()) {
                WxUser getUser = null;
                try {
                    getUser = mapper.readValue(userInfo, WxUser.class);
                    wxUserService.updateWxUserByOpenId(openid, getUser);
//                    repository.updateWxUserByOpenId(wxUser.getOpenId(),getUser.getNickName(),getUser.getGender(),
//                            getUser.getLanguage(),getUser.getCity(),getUser.getProvince(),getUser.getCountry(),
//                            getUser.getAvatarUrl());
                    getUser.setOpenId(wxUser.getOpenId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
                resultInfo.setMessage("用户储存成功");
                resultInfo.setData(getUser);
            } else {
                resultInfo.setCode(ResultInfo.RESULT_ERROR);
                resultInfo.setMessage("未收到userInfo");
            }
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("未收到openid");
        }

        return resultInfo;
    }

    @PostMapping(value = "/{openId}/updatePhone")
    public ResultInfo updatePhoneNumber(@RequestParam String phone, @PathVariable String openId) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            wxUserService.updatePhoneNumberByOpenId(phone, openId);
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("更新成功");
        } catch (Exception e) {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage(e.getMessage());
        }
        return resultInfo;
    }
}
