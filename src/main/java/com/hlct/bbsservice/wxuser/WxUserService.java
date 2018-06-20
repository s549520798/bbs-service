package com.hlct.bbsservice.wxuser;


public interface WxUserService {

    WxUser getWxUserByOpenId(String openId);

    int updateWxUserByOpenId(String openId, WxUser wxUser);
}
