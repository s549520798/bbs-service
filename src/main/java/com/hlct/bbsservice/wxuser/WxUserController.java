package com.hlct.bbsservice.wxuser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlct.bbsservice.common.ResultInfo;
import com.sun.javafx.util.Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultInfo getOpenId(@Param(value = "code") String code) {
        ResultInfo<LoginUser> resultInfo = new ResultInfo<>();
        log.info("收到的 code = " + code);
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("appid", "wx2f344148ddce304c")
                .add("secret", "7fab8b8de80a5e73aed96aaf26c3f5f1")
                .add("js_code", code)
                .add("grant_type", "authorization_code")
                .build();
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/sns/jscode2session")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
                String wxResult = response.body().string();
                log.info("从微信后台获取到的信息 ：" +  wxResult);
                WxIdInfo info = mapper.readValue(wxResult, WxIdInfo.class);

                WxUser wxUser = repository.findWxUserByOpenId(info.getOpenid());
                if (wxUser == null) {
                    System.out.println("用户不存在，新加用户");
                    WxUser user = new WxUser();
                    user.setOpenId(info.getOpenid());
                    user.setSessionKey(info.getSession_key());
                    repository.save(user);

                    resultInfo.setMessage("用户不存在，新加用户");
                    LoginUser loginUser = new LoginUser();
                    loginUser.setOldUser(false);
                    loginUser.setOpenid(info.getOpenid());
                    resultInfo.setData(loginUser);
                } else {
                    if (wxUser.getNickName() == null) {
                        log.info("存在openId ，但是不存在用户信息");
                        resultInfo.setMessage("存在openId ，但是不存在用户信息");
                        LoginUser loginUser = new LoginUser();
                        loginUser.setOldUser(false);
                        loginUser.setOpenid(wxUser.getOpenId());
                        resultInfo.setData(loginUser);
                    } else {
                        log.info("openId 和 用户数据都已经存在");
                        resultInfo.setMessage("openId 和 用户数据都已经存在");
                        LoginUser loginUser = new LoginUser();
                        loginUser.setOldUser(true);
                        loginUser.setOpenid(wxUser.getOpenId());
                        loginUser.setNickName(wxUser.getNickName());
                        loginUser.setCity(wxUser.getCity());
                        loginUser.setProvince(wxUser.getProvince());
                        loginUser.setGender(wxUser.getGender());
                        loginUser.setAvatarUrl(wxUser.getAvatarUrl());
                        loginUser.setCountry(wxUser.getCountry());
                        loginUser.setLanguage(wxUser.getLanguage());
                        resultInfo.setData(loginUser);
                    }
                }
            } else {
                resultInfo.setCode(ResultInfo.RESULT_ERROR);
                resultInfo.setMessage(response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("返回结果："+ resultInfo.toString());
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
}
