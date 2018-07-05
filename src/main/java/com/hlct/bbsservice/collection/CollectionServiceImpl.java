package com.hlct.bbsservice.collection;

import com.hlct.bbsservice.apply.ApplyRepository;
import com.hlct.bbsservice.comment.Comment;
import com.hlct.bbsservice.post.Post;
import com.hlct.bbsservice.post.PostPlus;
import com.hlct.bbsservice.post.PostRepository;
import com.hlct.bbsservice.wxuser.WxUser;
import com.hlct.bbsservice.wxuser.WxUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (repository.existsByPostIdAndOpenId(collection.getPostId(),collection.getOpenId())){
            return null;
        }else {
            return repository.save(collection);
        }

    }

    @Override
    public List<Collection> getCollectionsByOpenId(String openId) {
        return repository.findAllByOpenIdOrderByCollectionTimeDesc(openId);
    }

    @Override
    public List<PostPlus> getPostsByOpenId(String openId) {
        List<Collection> collections = repository.findAllByOpenIdOrderByCollectionTimeDesc(openId);
        List<PostPlus> postPluses = new ArrayList<>();
        //其中有同一帖子下的评论，排除掉
        List<Long> ids = new ArrayList<>();
        for (Collection collection :collections){
            if (!ids.contains(collection.getPostId())) ids.add(collection.getPostId());
        }
        List<Post> postList = postRepository.findAllById(ids);
        for (Post post: postList){
            PostPlus postPlus = new PostPlus();
            WxUser wxUser = wxUserRepository.findByOpenId(post.getOpenId());
            boolean isCalling = applyRepository.countByPostId(post.getId()) <= Integer.valueOf(post.getParticipatorMax());
            if (wxUser != null){
                postPlus.setWxUser(wxUser);
                postPlus.setPost(post);
                postPlus.setCalling(isCalling);
                postPluses.add(postPlus);
            }
        }
        return postPluses;
    }

    @Override
    public boolean isAlreadyCollected(Long postId, String openId) {
        return repository.existsByPostIdAndOpenId(postId,openId);
    }
    @Transactional
    @Override
    public boolean deleteCollected(Long postId, String openId) {
        if (repository.existsByPostIdAndOpenId(postId,openId)){
            repository.deleteByPostIdAndOpenId(postId,openId);
            return true;
        }else {
            return false;
        }
    }
}
