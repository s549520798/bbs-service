package com.hlct.bbsservice;

import com.hlct.bbsservice.taken.AccessTaken;
import com.hlct.bbsservice.taken.AccessTakenRepository;
import com.hlct.bbsservice.taken.AccessTakenServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessTakenTest {

    @Autowired
    AccessTakenRepository repository;
    @Autowired
    AccessTakenServiceImpl service;
    private Logger log = LoggerFactory.getLogger(AccessTakenTest.class);

    @Test
    public void testAT() {
        AccessTaken accessTaken = new AccessTaken();
        accessTaken.setAccessTaken("asdfghjkl");
        accessTaken.setExpiresIn(7200);
        AccessTaken accessTaken1 = new AccessTaken();
        accessTaken1.setAccessTaken("qwertyui");
        accessTaken1.setExpiresIn(7200);
        AccessTaken accessTaken2 = new AccessTaken();
        accessTaken2.setAccessTaken("zxcvbnm");
        accessTaken2.setExpiresIn(7200);
//        repository.save(accessTaken);
//        repository.save(accessTaken1);
//        repository.save(accessTaken2);
        log.error(repository.findTopByOrderByCreateTimeDesc().toString());
        Assert.assertEquals(accessTaken2.getAccessTaken(),repository.findTopByOrderByCreateTimeDesc().getAccessTaken());
        Assert.assertEquals(9,repository.count());
    }
    @Test
    public void testGetAccessTaken(){
        AccessTaken accessTaken = service.getUsableAccessTaken();
        Assert.assertNotNull(accessTaken);
    }
}
