package com.hlct.bbsservice.taken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hlct.bbsservice.common.ServiceProperties;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccessTakenServiceImpl implements AccessTakenService {

    private Logger log = LoggerFactory.getLogger(AccessTakenServiceImpl.class);
    private AccessTakenRepository repository;
    private final OkHttpClient okHttpClient;
    private ServiceProperties serviceProperties;
    private ObjectMapper objectMapper;

    @Autowired
    public AccessTakenServiceImpl(AccessTakenRepository repository, ServiceProperties serviceProperties, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.okHttpClient = new OkHttpClient();
        this.serviceProperties = serviceProperties;
    }

    @Override
    public AccessTaken getUsableAccessTaken() {
        AccessTaken accessTaken = repository.findTopByOrderByCreateTimeDesc();
        if (accessTaken != null && accessTaken.usable()) {
            return accessTaken;
        } else {
            RequestBody requestBody = new FormBody.Builder()
                    .add("appid", serviceProperties.getAppid())
                    .add("secret", serviceProperties.getSecret())
                    .add("grant_type", "client_credential")
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.weixin.qq.com/cgi-bin/token")
                    .post(requestBody)
                    .build();
            Response response = null;

            try {
                response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    //微信后台返回结果
                    String wxResult = response.body().string();
                    log.info("从微信后台获取到的信息 ：" + wxResult);
                    AccessTaken taken = objectMapper.readValue(wxResult, AccessTaken.class);
                    log.info("string 转 json" + taken.toString());
                    AccessTaken taken1 = repository.save(taken);
                    log.info("数据库中保存的access taken " + taken1);
                    return taken1;
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
