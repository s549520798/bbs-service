package com.hlct.bbsservice.post;

import com.hlct.bbsservice.common.ResultInfo;
import com.hlct.bbsservice.common.ResultPage;
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
    private final PostService postService;
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/save")
    public ResultInfo save(@ModelAttribute Post post) {
        ResultInfo<Post> resultInfo = new ResultInfo<>();
        log.info(post.toString());
        Post post1 = postService.savePost(post);
        if (post1 != null) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("发布成功");
            resultInfo.setData(post1);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("发布失败");
        }
        return resultInfo;
    }
    @PostMapping(value = "/saveAndNotify")
    public ResultInfo saveAndNotify(@ModelAttribute Post post,@RequestParam(name = "formId") String formId){
        ResultInfo resultInfo = new ResultInfo();
        return null;
    }

    @GetMapping(value = "/getAllPost")
    public ResultInfo<List<Post>> getAllPost() {
        ResultInfo<List<Post>> resultInfo = new ResultInfo<>();
        List<Post> list = postService.getAll();
        getListReturn(resultInfo, list);
        return resultInfo;
    }

    @GetMapping(value = "/{openId}/posts")
    public ResultInfo<List<Post>> getPostsByOpenId(@PathVariable String openId) {
        ResultInfo<List<Post>> resultInfo = new ResultInfo<>();
        log.info("获取到的openId 是：" + openId);
        List<Post> list = postService.getAllByOpenId(openId);
        getListReturn(resultInfo, list);
        return resultInfo;
    }

    @GetMapping(value = "/{page}/post")
    public ResultInfo<Page<Post>> getPosts(@PathVariable int page) {
        Page<Post> postPage = postService.getPostPage(page, 10);
        ResultInfo<Page<Post>> resultInfo = new ResultInfo<>();
//        log.info("一共post数目 ======" + postPage.getTotalElements());
//        log.info("一共分了多少页 =====" + postPage.getTotalPages());
//        log.info("当前页面所有数 =====" + postPage.getContent().size());
        resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
        resultInfo.setMessage("获取page成功");
        resultInfo.setData(postPage);
        return resultInfo;
    }
    @GetMapping(value = "/{page}/getPostInPage")
    public ResultInfo<ResultPage<PostPlus>> getPostsInPage(@PathVariable int page){
        ResultInfo<ResultPage<PostPlus>> resultInfo = new ResultInfo<>();
        ResultPage<PostPlus> resultPage = postService.getPostInPage(page);
        resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
        resultInfo.setMessage("获取成功");
        resultInfo.setData(resultPage);
        return resultInfo;
    }

    @GetMapping(value = "/{page}/getPosts")
    public ResultInfo<List<PostPlus>> getPostsWithUser(@PathVariable int page) {
        ResultInfo<List<PostPlus>> resultInfo = new ResultInfo<>();
        List<PostPlus> list = postService.getPagePosts(page);
        if (list != null && list.size() > 0) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("获取成功！");
            resultInfo.setData(list);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("没有获取到数据");
        }
        return resultInfo;
    }

    @GetMapping(value = "/{postId}/getPostAndAuthor")
    public ResultInfo<PostPlus> getPostAndAuthor(@PathVariable long postId) {
        ResultInfo<PostPlus> resultInfo = new ResultInfo<>();
        resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
        resultInfo.setMessage("获取成功");
        resultInfo.setData(postService.getPostAndAuthor(postId));
        return resultInfo;
    }

    private void getListReturn(ResultInfo<List<Post>> resultInfo, List<Post> list) {
        if (list != null && list.size() > 0) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("获取成功");
            resultInfo.setData(list);
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("获取失败");
        }
    }


}
