package com.hlct.bbsservice.post;

import com.hlct.bbsservice.common.ResultInfo;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {
    private final PostRepository repository;
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/save")
    public ResultInfo save(@ModelAttribute Post post) {
        ResultInfo<Post> resultInfo = new ResultInfo<>();
        log.info(post.toString());
        if (post != null) {
            Post post1 = repository.save(post);
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("发布成功");
            resultInfo.setData(post1);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("发布失败");
        }
        return resultInfo;
    }

    @GetMapping(value = "getAllPost")
    public ResultInfo<List<Post>> getAllPost() {
        ResultInfo<List<Post>> resultInfo = new ResultInfo<>();
        List<Post> list = repository.findAll();
        getListReturn(resultInfo, list);
        return resultInfo;
    }

    @PostMapping(value = "getPostsByOpenId")
    public ResultInfo<List<Post>> getPostsByOpenId(@PathVariable String openId) {
        ResultInfo<List<Post>> resultInfo = new ResultInfo<>();
        log.info("获取到的openId 是：" + openId);
        List<Post> list = repository.findPostsByOpenId(openId);
        getListReturn(resultInfo, list);
        return resultInfo;
    }

    private void getListReturn(ResultInfo<List<Post>> resultInfo, List<Post> list) {
        if (list != null && list.size() > 0){
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("获取成功");
            resultInfo.setData(list);
        }else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("获取失败");
        }
    }

}
