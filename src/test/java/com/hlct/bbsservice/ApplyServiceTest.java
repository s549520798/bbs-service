package com.hlct.bbsservice;

import com.hlct.bbsservice.apply.Apply;
import com.hlct.bbsservice.apply.ApplyRepository;
import com.hlct.bbsservice.apply.ApplyService;
import com.hlct.bbsservice.apply.ApplyServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyServiceTest {
    @Autowired
    ApplyServiceImpl applyService;
    @Autowired
    ApplyRepository applyRepository;
    @Test
    public void testApply(){
        Apply apply = new Apply();
        apply.setName("测试人一");
        apply.setEcName("张三");
        apply.setPhoneNumber("123456789");
        apply.setEcPhone("112233456");
        apply.setPostId((long)16);
        apply.setOpenId("oQK8Y0bG1WPAYHfbxG687pek_LZY");
        applyRepository.save(apply);
        Apply apply1 = new Apply();
        apply1.setName("测试人一");
        apply1.setEcName("张三");
        apply1.setPhoneNumber("123456789");
        apply1.setEcPhone("112233456");
        apply1.setPostId((long)16);
        apply1.setOpenId("oQK8Y0bG1WPAYHfbxG687pek_YYY");
        applyRepository.save(apply1);
        Assert.assertEquals(3,applyRepository.count());
    }
}
