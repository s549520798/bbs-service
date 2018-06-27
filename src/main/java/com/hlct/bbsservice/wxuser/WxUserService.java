package com.hlct.bbsservice.wxuser;


public interface WxUserService {
    /**
     * 根据 小程序端传过来的 code ,到微信后台请求 用户唯一标识openID
     * @param code 小程序端传过来的code
     * @return 用户唯一标识
     */
    WxUser getOpenId(String code);

    WxUser getWxUserByOpenId(String openId);

    int updateWxUserByOpenId(String openId, WxUser wxUser);

    /**
     * 根据openId 获取 user
     * @param openId openId
     * @return wxUser
     */
    WxUser findByOpenId(String openId);
}
