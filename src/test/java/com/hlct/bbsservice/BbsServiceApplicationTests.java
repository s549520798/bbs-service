package com.hlct.bbsservice;

import com.hlct.bbsservice.common.ServiceProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsServiceApplicationTests {

    @Autowired
    private ServiceProperties serviceProperties;

    @Test
    public void contextLoads() {
    }
    @Test
    public void testServiceProperties(){
        Assert.assertEquals(serviceProperties.getServiceDomain(),"http://192.168.1.105:8080/");
        Assert.assertEquals(serviceProperties.getUploadPath(),"F:/app/bbs/");
        Assert.assertEquals(serviceProperties.getImagePath(),"images/");
    }

}
