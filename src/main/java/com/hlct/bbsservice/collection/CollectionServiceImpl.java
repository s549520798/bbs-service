package com.hlct.bbsservice.collection;

import com.hlct.bbsservice.post.PostPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService{

    private final CollectionRepository repository;
    @Autowired
    public CollectionServiceImpl(CollectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection save(Collection collection) {
        return repository.save(collection);
    }

    @Override
    public List<Collection> getCollectionsByOpenId(String openId) {
        return null;
    }

    @Override
    public List<PostPlus> getPostsByOpenId(String openId) {
        return null;
    }
}
