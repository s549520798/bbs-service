package com.hlct.bbsservice.apply;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlct.bbsservice.post.Post;
import com.hlct.bbsservice.post.PostPlus;
import com.hlct.bbsservice.post.PostRepository;
import com.hlct.bbsservice.template.Template;
import com.hlct.bbsservice.template.TemplateConstant;
import com.hlct.bbsservice.template.TemplateParameter;
import com.hlct.bbsservice.template.TemplateServiceImpl;
import com.hlct.bbsservice.wxuser.WxUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ApplyServiceImpl implements ApplyService {


    private final ApplyRepository repository;
    private final PostRepository postRepository;
    private final WxUserRepository wxUserRepository;
    private final TemplateServiceImpl templateService;
    private ObjectMapper objectMapper;

    @Autowired
    public ApplyServiceImpl(ApplyRepository repository, PostRepository postRepository, WxUserRepository wxUserRepository, TemplateServiceImpl templateService, ObjectMapper objectMapper) {
        this.repository = repository;
        this.postRepository = postRepository;
        this.wxUserRepository = wxUserRepository;
        this.templateService = templateService;
        this.objectMapper = objectMapper;
    }


    @Override
    public List<Apply> findApplyByPostId(Long postId) {
        return repository.findAllByPostId(postId);
    }

    @Override
    public int countByPostId(Long postId) {
        return repository.countByPostId(postId);
    }

    @Transactional
    @Override
    public Apply save(Apply apply) {
        Post post = postRepository.getOne(apply.getPostId());
        if (apply.getOpenId().equals(post.getOpenId())) {
            //IsAuthor
            return null;
        } else if (repository.existsByPostIdAndOpenId(apply.getPostId(), apply.getOpenId())) {
            //hasApplied
            return null;
        } else {
            wxUserRepository.updatePhoneByOpenId(apply.getPhoneNumber(), apply.getName(), apply.getOpenId());
            return repository.save(apply);
        }
    }

    @Override
    public Apply findOne(long applyId) {
        return repository.getOne(applyId);
    }

    @Override
    public boolean notifyAuthor(String formId, Apply apply) {
        Post post = postRepository.getOne(apply.getPostId());
        String postJson = "";
        try {
            postJson = objectMapper.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String applyTime = dateFormat.format(apply.getApplyTime());
        Map<String,TemplateParameter> data = new LinkedHashMap<>();
        data.put("keyword1", new TemplateParameter(post.getTitle()));    //活动标题
        data.put("keyword2", new TemplateParameter(applyTime));//申请时间
        data.put("keyword3", new TemplateParameter(apply.getMessage()));//说明
        data.put("keyword4", new TemplateParameter(apply.getPhoneNumber()));//电话号码
        data.put("keyword5", new TemplateParameter(apply.getName()));//申请人
        Template template = new Template();
        template.setToUser(post.getOpenId()); //发帖人openId
        template.setTemplateId(TemplateConstant.TEMPLATE_TO_REVIEW);
        template.setFormId(formId);           //表单Id
        template.setPage("/pages/activity/activity?post=" + postJson);
        template.setEmphasisKeyword("keyword1.DATA");
        template.setData(data);
        return templateService.applyNotify(template);
    }

    @Override
    public List<Apply> findAllByOpenId(String openId) {
        return repository.findAllByOpenId(openId);
    }

    @Override
    public List<PostPlus> findApplyPostsByOpenId(String openId) {
        List<Apply> applies = repository.findAllByOpenId(openId);
        List<PostPlus> postPluses = new ArrayList<>();
        for (Apply apply : applies) {
            Post post = postRepository.getOne(apply.getPostId());
            PostPlus postPlus = new PostPlus();
            postPlus.setPost(post);
            postPlus.setWxUser(wxUserRepository.findWxUserByOpenId(post.getOpenId()));
            boolean isCalling = repository.countByPostId(post.getId()) <= Integer.valueOf(post.getParticipatorMax());
            postPlus.setCalling(isCalling);
            postPluses.add(postPlus);
        }
        return postPluses;
    }

    @Override
    public List<ApplyUser> findApplyPersonByPostId(long postId) {
        List<Apply> applies = repository.findAllByPostId(postId);
        List<ApplyUser> wxUsers = new ArrayList<>();
        getApplyUsers(wxUsers, applies);
        return wxUsers;
    }

    @Override
    public boolean hasApplied(long postId, String openId) {
        return repository.existsByPostIdAndOpenId(postId, openId);
    }

    @Override
    public List<ApplyUser> getApplicants(long postId, String openId) {
        //检查用户是不是该 post 的发布者
        boolean isAuthor = postRepository.existsByOpenIdAndId(openId, postId);
        List<ApplyUser> list = new ArrayList<>();
        if (isAuthor) {
            List<Apply> applies = repository.findAllByPostId(postId);
            getApplyUsers(list, applies);
            return list;
        } else {
            return null;
        }
    }

    @Override
    public List<ApplyUser> getApplicants(long postId) {
        List<ApplyUser> list = new ArrayList<>();
        List<Apply> applies = repository.findAllByPostId(postId);
        getApplyUsers(list, applies);
        return list;
    }

    @Transactional
    @Override
    public int updateStatusByOpenIdAndApplyId(String openId, long applyId, String status) {
        //验证openID是否是发布人
        Apply apply = repository.getOne(applyId);
        boolean isAuthor = postRepository.existsByOpenIdAndId(openId, apply.getPostId());
        if (isAuthor && status != null){
            int hasConfirm;
            int index;
            if (status.equals("修改")){
                hasConfirm = 0;
                index = repository.updateStatus(applyId,"未确认",hasConfirm);
            }else if (status.equals("通过") || status.equals("拒绝")){
                hasConfirm = 1;
                index = repository.updateStatus(applyId,status,hasConfirm);
            }else {
                index = 0;
            }
            return index;
        }else {
            return -1;
        }
    }



    private void getApplyUsers(List<ApplyUser> list, List<Apply> applies) {
        for (Apply apply : applies) {
            ApplyUser applyUser = new ApplyUser();
            applyUser.setApply(apply);
            applyUser.setWxUser(wxUserRepository.findByOpenId(apply.getOpenId()));
            list.add(applyUser);
        }
    }


}
