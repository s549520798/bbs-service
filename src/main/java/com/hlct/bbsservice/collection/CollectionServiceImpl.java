package com.hlct.bbsservice.collection;

import com.hlct.bbsservice.apply.ApplyRepository;
import com.hlct.bbsservice.post.Post;
import com.hlct.bbsservice.post.PostPlus;
import com.hlct.bbsservice.post.PostRepository;
import com.hlct.bbsservice.wxuser.WxUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService{

    private final CollectionRepository repository;
    private final PostRepository postRepository;
    private final WxUserRepository wxUserRepository;
    private final ApplyRepository applyRepository;
    @Autowired
    public CollectionServiceImpl(CollectionRepository repository, PostRepository postRepository, WxUserRepository wxUserRepository, ApplyRepository applyRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
        this.wxUserRepository = wxUserRepository;
        this.applyRepository = applyRepository;
    }

    @Override
    public Collection save(Collection collection) {
        return repository.save(collection);
    }

    @Override
    public List<Collection> getCollectionsByOpenId(String openId) {
        return repository.findAllByOpenIdOrderByCollectionTimeDesc(openId);
    }

    @Override
    public List<PostPlus> getPostsByOpenId(String openId) {
        List<Collection> collections = repository.findAllByOpenIdOrderByCollectionTimeDesc(openId);
        List<PostPlus> postPluses = new ArrayList<>();
        for (Collection collection :collections){
            PostPlus postPlus = new PostPlus();
            Post post = postRepository.getOne(collection.getPostId());
            postPlus.setPost(post);
            postPlus.setWxUser(wxUserRepository.findWxUserByOpenId(post.getOpenId()));
            boolean isCalling = applyRepository.countByPostId(post.getId()) <= Integer.valueOf(post.getParticipatorMax());
            postPlus.setCalling(isCalling);
            postPluses.add(postPlus);

        }
        return postPluses;
    }
}
