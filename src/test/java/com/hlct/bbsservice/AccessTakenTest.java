package com.hlct.bbsservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlct.bbsservice.taken.AccessTaken;
import com.hlct.bbsservice.taken.AccessTakenRepository;
import com.hlct.bbsservice.taken.AccessTakenServiceImpl;
import com.hlct.bbsservice.template.Template;
import com.hlct.bbsservice.template.TemplateParameter;
import com.hlct.bbsservice.template.TemplateServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessTakenTest {

    @Autowired
    AccessTakenRepository repository;
    @Autowired
    AccessTakenServiceImpl service;
    @Autowired
    ObjectMapper objectMapper;
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
        Assert.assertEquals(accessTaken2.getAccessTaken(), repository.findTopByOrderByCreateTimeDesc().getAccessTaken());
        Assert.assertEquals(9, repository.count());

    }

    @Test
    public void testGetAccessTaken() {
        Template template = new Template();
        template.setToUser("user_openID");
        template.setFormId("form_id");
        template.setPage("/apge/afasf");
        template.setTemplateId("template_id");
        template.setEmphasisKeyword("sdknglkasdga");
        Map<String, TemplateParameter> data = new HashMap<>();
        data.put("keyword1", new TemplateParameter("keywordssss1"));
        data.put("keyword2", new TemplateParameter("keywordssss2"));
        data.put("keyword3", new TemplateParameter("keywordssss3"));
        template.setData(data);
        try {
            String text = objectMapper.writeValueAsString(template);
            log.info(text);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        AccessTaken accessTaken = service.getUsableAccessTaken();
        log.info(accessTaken.toString());
        Assert.assertNotNull(accessTaken);
    }




}
