package com.hlct.bbsservice.collection;

import com.hlct.bbsservice.common.ResultInfo;
import com.hlct.bbsservice.post.PostPlus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/collection")
public class CollectionController {

    private final CollectionService service;
    private static final Logger log = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    public CollectionController(CollectionServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/save")
    public ResultInfo<Collection> save(@ModelAttribute Collection collection) {
        Collection collection1 = service.save(collection);
        ResultInfo<Collection> resultInfo = new ResultInfo<>();
        if (collection1 != null) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("收藏成功");
            resultInfo.setData(collection1);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("已经收藏过了");
        }
        return resultInfo;
    }

    @GetMapping(value = "/{openId}/getCollections")
    public ResultInfo<List<PostPlus>> getCollectedPosts(@PathVariable String openId) {
        ResultInfo<List<PostPlus>> resultInfo = new ResultInfo<>();
        List<PostPlus> list = service.getPostsByOpenId(openId);
        if (list.size() > 0) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("获取收藏列表成功");
            resultInfo.setData(list);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("获取收藏列表失败");
        }
        return resultInfo;
    }

    @PostMapping(value = "/{openId}/isCollected")
    public ResultInfo<Boolean> isAlreadyCollected(@RequestParam Long postId, @PathVariable String openId) {
        ResultInfo<Boolean> resultInfo = new ResultInfo<>();
        boolean isCollected = service.isAlreadyCollected(postId, openId);
        if (isCollected) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("已经收藏过了");
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("还没有收藏");
        }
        return resultInfo;
    }

    @PostMapping(value = "/{openId}/deleteCollected")
    public ResultInfo<Boolean> deleteCollect(@RequestParam Long postId, @PathVariable String openId) {
        ResultInfo<Boolean> resultInfo = new ResultInfo<>();
        boolean isDelte = service.deleteCollected(postId, openId);
        if (isDelte) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("取消收藏成功");
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("没有收藏过");
        }
        return resultInfo;
    }
}
