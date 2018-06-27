package com.hlct.bbsservice;

import com.hlct.bbsservice.post.PostServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostServiceImpl postService;
    @Test
    public void testPostService(){
        Assert.assertEquals(postService.getPagePosts(0).size(),2);


    }
}
