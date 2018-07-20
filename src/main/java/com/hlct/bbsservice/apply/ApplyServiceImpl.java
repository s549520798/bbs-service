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
        wxUserRepository.updatePhoneByOpenId(apply.getPhoneNumber(),apply.getName(),apply.getOpenId());
        return repository.save(apply);
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
        for (Apply apply : applies) {
            ApplyUser applyUser = new ApplyUser();
            applyUser.setApply(apply);
            applyUser.setWxUser(wxUserRepository.findByOpenId(apply.getOpenId()));
            wxUsers.add(applyUser);
        }
        return wxUsers;
    }

    @Override
    public boolean hasApplied(long postId, String openId) {
        return repository.existsByPostIdAndOpenId(postId,openId);
    }


}
