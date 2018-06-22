package com.hlct.bbsservice.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {

    private final JdbcTemplate jdbcTemplate;
    private PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository,JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Post savePost(Post post) {
        return repository.save(post);
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

    @Override
    public List<PostAndUser> getAllPostsWithUser() {
        String sql = "SELECT * FROM wx_user u Inner JOIN post p WHERE u.open_id = p.open_id";
        List<PostAndUser> list = jdbcTemplate.query(sql,new Object[]{},new BeanPropertyRowMapper<>(PostAndUser.class));
        return list;
    }

}
