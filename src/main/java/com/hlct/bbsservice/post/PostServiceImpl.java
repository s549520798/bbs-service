package com.hlct.bbsservice.post;


import com.hlct.bbsservice.apply.ApplyRepository;
import com.hlct.bbsservice.wxuser.WxUser;
import com.hlct.bbsservice.wxuser.WxUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {

    private Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    private final JdbcTemplate jdbcTemplate;
    private PostRepository repository;
    private WxUserRepository wxUserRepository;
    private final ApplyRepository applyRepository;

    @Autowired
    public PostServiceImpl(PostRepository repository, JdbcTemplate jdbcTemplate,
                           WxUserRepository wxUserRepository, ApplyRepository applyRepository) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
        this.wxUserRepository = wxUserRepository;
        this.applyRepository = applyRepository;
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
    public Page<Post> getPostPage(int page,int pageCount) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC,"postTime"));
        PageRequest pageRequest =  PageRequest.of(page,pageCount,sort);
        return  repository.findAll(pageRequest);
    }

    @Override
    public List<PostPlus> getPagePosts(int page) {
        //规定每页10条数据
        int pageSize = 10;
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC,"postTime"));
        PageRequest pageRequest =  PageRequest.of(page,pageSize,sort);
        Page<Post> postPage = repository.findAll(pageRequest);
//        log.info("一共post数目 ======" + postPage.getTotalElements());
//        log.info("一共分了多少页 =====" + postPage.getTotalPages());
//        log.info("当前页面所有数 =====" + postPage.getContent().size());
        List<Post> list = postPage.getContent();
        if (postPage.getContent().size() > 0){
            List<PostPlus> postPluses = new ArrayList<>();
            for (Post post : list){
                PostPlus postPlus = new PostPlus();
                postPlus.setPost(post);
                postPlus.setWxUser(wxUserRepository.findWxUserByOpenId(post.getOpenId()));
                boolean isCalling = applyRepository.countByPostId(post.getId()) <= Integer.valueOf(post.getParticipatorMax());
                postPlus.setCalling(isCalling);
                postPluses.add(postPlus);
            }
            return postPluses;
        }else {
            return null;
        }
    }

    @Override
    public List<Post> getAllByOpenId(String openId) {

        return repository.findAllByOpenId(openId);
    }

    @Override
    public PostPlus getPostAndAuthor(long postId) {
        PostPlus postPlus = new PostPlus();
        Post post = repository.getOne(postId);
        postPlus.setPost(post);
        WxUser wxUser = wxUserRepository.findByOpenId(post.getOpenId());
        postPlus.setWxUser(wxUser);
        //TODO 找出是否正在召集，根据通过人数进行确认。
        return postPlus;
    }

}
