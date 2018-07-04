package com.hlct.bbsservice.comment;

import com.hlct.bbsservice.common.ResultInfo;
import com.hlct.bbsservice.post.PostPlus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/save")
    public ResultInfo<Comment> save(@ModelAttribute Comment comment) {
        ResultInfo<Comment> resultInfo = new ResultInfo<>();
        Comment com = commentService.save(comment);

        if (com != null) {
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("评论成功");
            resultInfo.setData(com);
            log.info("提交评论成功： " + com.toString());
        } else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("评论失败");
        }
        return resultInfo;
    }

    @GetMapping(value = "/{postId}/getComments")
    public ResultInfo<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        ResultInfo<List<Comment>> resultInfo = new ResultInfo<>();
        List<Comment> comments = commentService.getCommentByPostId(postId);
        //comments 不为 null ,但size可能是0
        resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
        resultInfo.setMessage("获取评论成功");
        resultInfo.setData(comments);
        return resultInfo;
    }

    @GetMapping(value = "/{openId}/getPosts")
    public ResultInfo<List<PostPlus>> getPostByOpenId(@PathVariable String openId){
        ResultInfo<List<PostPlus>> resultInfo = new ResultInfo<>();
        List<PostPlus> list =  commentService.getPostByOpenId(openId);
        resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
        resultInfo.setMessage("获取帖子成功");
        resultInfo.setData(list);
        return resultInfo;
    }
}
