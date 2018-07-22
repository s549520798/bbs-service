package com.hlct.bbsservice.apply;

import com.hlct.bbsservice.post.Post;
import com.hlct.bbsservice.post.PostPlus;
import com.hlct.bbsservice.post.PostRepository;
import com.hlct.bbsservice.wxuser.WxUser;
import com.hlct.bbsservice.wxuser.WxUserRepository;
import javafx.collections.transformation.SortedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceImpl implements ApplyService {


    private final ApplyRepository repository;
    private final PostRepository postRepository;
    private final WxUserRepository wxUserRepository;

    @Autowired
    public ApplyServiceImpl(ApplyRepository repository, PostRepository postRepository, WxUserRepository wxUserRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
        this.wxUserRepository = wxUserRepository;
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
