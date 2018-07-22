package com.hlct.bbsservice;


import com.hlct.bbsservice.wxuser.WxUser;
import com.hlct.bbsservice.wxuser.WxUserRepository;
import com.hlct.bbsservice.wxuser.WxUserService;
import com.hlct.bbsservice.wxuser.WxUserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxUserTest {
    @Autowired
    WxUserServiceImpl wxUserService;
    @Autowired
    WxUserRepository wxUserRepository;
    @Test
    public void  testSave(){
        WxUser wxUser = new WxUser();
        wxUser.setOpenId("oQK8Y0bG1WPAYHfbxG687pek_LZY");
        wxUser.setNickName("测人一");
        wxUser.setGender(2);
        wxUser.setAvatarUrl("https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epUXpvIbKKeXUpS5n3WhrC0tc72ianY8qyhXZGJDlsKIUamDXLJZcZIxHQFOQ6vjGvqiaPG6ujzo8kA/132");
        wxUser.setCity("深圳");
        wxUser.setProvince("广东");
        wxUser.setLanguage("zh_CN");
        wxUser.setCountry("中国");
        WxUser wxUser1 = new WxUser();
        wxUser1.setOpenId("oQK8Y0bG1WPAYHfbxG687pek_YYY");
        wxUser1.setNickName("测人二");
        wxUser1.setGender(1);
        wxUser1.setAvatarUrl("https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epUXpvIbKKeXUpS5n3WhrC0tc72ianY8qyhXZGJDlsKIUamDXLJZcZIxHQFOQ6vjGvqiaPG6ujzo8kA/132");
        wxUser1.setCity("深圳");
        wxUser1.setProvince("广东");
        wxUser1.setLanguage("zh_CN");
        wxUser1.setCountry("中国");
        wxUserRepository.save(wxUser);
        wxUserRepository.save(wxUser1);
        Assert.assertEquals(3,wxUserRepository.count());
    }
}
