package com.hlct.bbsservice.wxuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl implements WxUserService {


    private JdbcTemplate jdbcTemplate;
    @Autowired
    public WxUserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
}
