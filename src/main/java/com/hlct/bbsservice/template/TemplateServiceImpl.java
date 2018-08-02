package com.hlct.bbsservice.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlct.bbsservice.taken.AccessTaken;
import com.hlct.bbsservice.taken.AccessTakenServiceImpl;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

    private static final Logger log = LoggerFactory.getLogger(TemplateServiceImpl.class);

    private final AccessTakenServiceImpl accessTakenService;
    private OkHttpClient httpClient;
    private ObjectMapper objectMapper;

    @Autowired
    public TemplateServiceImpl(AccessTakenServiceImpl accessTakenService, ObjectMapper objectMapper) {
        this.accessTakenService = accessTakenService;
        this.objectMapper = objectMapper;
        httpClient = new OkHttpClient();
    }

    @Override
    public boolean applyNotify(Template template) {
        AccessTaken accessTaken = accessTakenService.getUsableAccessTaken();
        if (accessTaken == null) {
            return false;
        } else {
            try {
                String data = objectMapper.writeValueAsString(template);
                log.info("模板消息data ======== " + data );
                RequestBody requestBody = FormBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        data);
//                RequestBody requestBody = new FormBody.Builder()
//                        .add("touser", template.getToUser())
//                        .add("template_id", template.getTemplateId())
//                        .add("page", template.getPage())
//                        .add("form_id", "authorization_code")
//                        .add("data", data)
//                        .add("emphasis_keyword", template.getEmphasisKeyword())
//                        .build();
                Request request = new Request.Builder()
                        .url("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessTaken.getAccessTaken())
                        .post(requestBody)
                        .build();
                Response response;

                response = httpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null){
                        String body = response.body().string();
                        RespEntity responseEntity = objectMapper.readValue(body,RespEntity.class);
                        if (responseEntity.errcode == 0){
                            log.info("======发送模板消息成功！！！======");
                            return true;
                        }else {
                            log.error(responseEntity.getErrmsg());
                            return false;
                        }
                    }else {
                        return false;
                    }
                } else {
                    log.error(response.message());
                    return false;
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
                return false;
            }
        }
    }


    public static class RespEntity{
        private int errcode;
        private String errmsg;

        public RespEntity() {
        }

        public RespEntity(int errcode, String errmsg) {
            this.errcode = errcode;
            this.errmsg = errmsg;
        }

        public int getErrcode() {
            return errcode;
        }

        public void setErrcode(int errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }
}


