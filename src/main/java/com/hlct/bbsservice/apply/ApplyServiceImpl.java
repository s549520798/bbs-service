package com.hlct.bbsservice.apply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApplyServiceImpl implements ApplyService {


    private final ApplyRepository repository;
    @Autowired
    public ApplyServiceImpl(ApplyRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Apply> findApplyByPostId(Long postId) {
        return repository.findAllByPostId(postId);
    }

    @Override
    public int countByPostId(Long postId) {
        return repository.countByPostId(postId);
    }

    @Override
    public Apply save(Apply apply) {
        return repository.save(apply);
    }
}
