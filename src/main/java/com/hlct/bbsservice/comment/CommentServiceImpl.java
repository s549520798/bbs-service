package com.hlct.bbsservice.comment;

import com.hlct.bbsservice.apply.ApplyRepository;
import com.hlct.bbsservice.post.Post;
import com.hlct.bbsservice.post.PostPlus;
import com.hlct.bbsservice.post.PostRepository;
import com.hlct.bbsservice.wxuser.WxUser;
import com.hlct.bbsservice.wxuser.WxUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final PostRepository postRepository;
    private final WxUserRepository wxUserRepository;
    private final ApplyRepository applyRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository repository, PostRepository postRepository, WxUserRepository wxUserRepository, ApplyRepository applyRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
        this.wxUserRepository = wxUserRepository;
        this.applyRepository = applyRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public List<Comment> getCommentByPostId(Long postId) {
        return repository.findAllByPostIdOrderByCommentTimeDesc(postId);
    }

    @Override
    public List<PostPlus> getPostByOpenId(String openId) {
        List<Comment> comments = repository.findAllByOpenIdOrderByCommentTimeDesc(openId);
        //其中有同一帖子下的评论，排除掉
        List<Long> ids = new ArrayList<>();
        for (Comment comment : comments) {
            if (!ids.contains(comment.getPostId())) ids.add(comment.getPostId());
        }
        List<Post> postList = postRepository.findAllById(ids);
        List<PostPlus> postPluses = new ArrayList<>();
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
}
