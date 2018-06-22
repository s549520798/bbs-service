package com.hlct.bbsservice.post;

import com.hlct.bbsservice.common.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {
    private final PostRepository repository;
    private final PostService postService;
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostRepository repository,PostServiceImpl postService) {
        this.repository = repository;
        this.postService = postService;
    }

    @PostMapping(value = "/save")
    public ResultInfo save(@ModelAttribute Post post) {
        ResultInfo<Post> resultInfo = new ResultInfo<>();
        log.info(post.toString());
        if (post != null) {
            Post post1 = postService.savePost(post);
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("发布成功");
            resultInfo.setData(post1);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("发布失败");
        }
        return resultInfo;
    }

    @GetMapping(value = "/getAllPost")
    public ResultInfo<List<Post>> getAllPost() {
        ResultInfo<List<Post>> resultInfo = new ResultInfo<>();
        List<Post> list = postService.getAll();
        getListReturn(resultInfo, list);
        return resultInfo;
    }

    @PostMapping(value = "/getPostsByOpenId")
    public ResultInfo<List<Post>> getPostsByOpenId(@RequestParam String openId) {
        ResultInfo<List<Post>> resultInfo = new ResultInfo<>();
        log.info("获取到的openId 是：" + openId);
        List<Post> list = postService.getPostsByOpenId(openId);
        getListReturn(resultInfo, list);
        return resultInfo;
    }

    @GetMapping(value = "/{page}/post")
    public ResultInfo<Page<Post>> getPosts(@PathVariable int page){
        int pageSize = 10;
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC,"postTime"));
        PageRequest pageRequest =  PageRequest.of(page,pageSize,sort);
        Page<Post> postPage = repository.findAll(pageRequest);
        ResultInfo<Page<Post>> resultInfo = new ResultInfo<>();
        log.info("一共post数目 ======" + postPage.getTotalElements());
        log.info("一共分了多少页 =====" + postPage.getTotalPages());
        log.info("当前页面所有数 =====" + postPage.getContent().size());
        resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
        resultInfo.setMessage("获取page成功");
        resultInfo.setData(postPage);
        return resultInfo;
    }
    @GetMapping(value = "/getPostWithUser")
    public ResultInfo<List<PostAndUser>> getPostsWithUser(){
        ResultInfo<List<PostAndUser>> resultInfo = new ResultInfo<>();
        List<PostAndUser> list = postService.getAllPostsWithUser();
        if (list != null && list.size() > 0){
            log.info("size = " + list.size());
            for (PostAndUser postAndUser : list){
                log.info(postAndUser.toString());
            }
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("获取成功！");
            resultInfo.setData(list);
        }else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("没有获取到数据");
        }
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
