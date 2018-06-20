package com.hlct.bbsservice.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {

    private PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void savePost(Post post) {
        repository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Post> getPostsByOpenId(String openId) {
        return repository.findPostsByOpenId(openId);
    }
}
