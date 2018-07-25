package com.hlct.bbsservice.wxuser;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;

@Service
public class WxUserServiceImpl implements WxUserService {

    private Logger log = LoggerFactory.getLogger(WxUserServiceImpl.class);
    private JdbcTemplate jdbcTemplate;
    private final OkHttpClient okHttpClient;
    private ObjectMapper mapper;
    private final WxUserRepository repository;

    @Autowired
    public WxUserServiceImpl(JdbcTemplate jdbcTemplate, ObjectMapper mapper, WxUserRepository repository) {
        this.jdbcTemplate = jdbcTemplate;
        this.okHttpClient = new OkHttpClient();
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public WxUser getOpenId(String code) {
        WxUser result;

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
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                //微信后台返回结果
                String wxResult = response.body().string();
                log.info("从微信后台获取到的信息 ：" +  wxResult);
                WxIdInfo info = mapper.readValue(wxResult, WxIdInfo.class);
                //数据库查找是否存在此openid 用户
                boolean isExists = repository.existsByOpenId(info.getOpenid());
                if (!isExists) {
                    //如果不存在，将 此用户储存为新用户，并返回只带openID的用户信息
                    log.info("用户不存在，新加用户");
                    WxUser user = new WxUser();
                    user.setOpenId(info.getOpenid());
                    user.setSessionKey(info.getSession_key());
                    return repository.save(user);


                } else {
                    // 此用户 之前存在在数据库中， 但是不确定此用户是否已将用户信息授权
                   result = repository.findWxUserByOpenId(info.getOpenid());
                   return result;
                }
            } else {
                //从微信后台获取用户信息失败,返回null。
                return  null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WxUser getWxUserByOpenId(String openId) {
        String sql = "SELECT * FROM wx_user WHERE open_id = ?";
        return jdbcTemplate.queryForObject(sql, new String[]{openId},new BeanPropertyRowMapper<>(WxUser.class));

    }

    @Override
    public int updateWxUserByOpenId(String openId, WxUser wxUser) {
        String sql = "UPDATE wx_user SET nick_name = ?,gender = ?,language = ?,city = ?,province = ?," +
                "country = ?,avatar_url = ? WHERE open_id = ?";
        return jdbcTemplate.update(sql,wxUser.getNickName(),wxUser.getGender(),wxUser.getLanguage(),
        wxUser.getCity(),wxUser.getProvince(),wxUser.getCountry(),wxUser.getAvatarUrl(),openId);
    }

    @Override
    public WxUser findByOpenId(String openId) {
        return repository.findByOpenId(openId);
    }
    @Transactional
    @Override
    public void updatePhoneNumberByOpenId(String phone, String openId) {
        repository.updatePhoneNumberByOpenId(phone,openId);
    }


}
