package com.hlct.bbsservice.comment;

import com.hlct.bbsservice.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    @Autowired
    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
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
    public List<Post> getPostByOpenId(String openId) {
        repository.findAllByOpenIdOrderByCommentTimeDesc(openId);
        return null;
    }
}
