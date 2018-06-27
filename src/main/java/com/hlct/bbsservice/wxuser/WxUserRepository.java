package com.hlct.bbsservice.wxuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WxUserRepository extends JpaRepository<WxUser, Long> {

    WxUser findWxUserByOpenId(String openId);

    WxUser findByOpenId(String openId);

    boolean existsByOpenId(String openId);

    @Modifying
    @Query("update WxUser wxu set wxu.nickName = :nickName , wxu.gender = :gender,wxu.language = " +
            ":language,wxu.city = :city,wxu.province = :province,wxu.country = :country,wxu.avatarUrl" +
            " = :avatarUrl  where wxu.openId = :openid")
    void updateWxUserByOpenId(@Param(value = "openid") String openid, @Param(value = "nickName") String nickName,
                              @Param(value = "gender") int gender, @Param(value = "language") String language,
                              @Param(value = "city") String city, @Param(value = "province") String province,
                              @Param(value = "country") String country, @Param(value = "avatarUrl") String avatarUrl);
}
