package com.hlct.bbsservice.apply;

import com.hlct.bbsservice.post.PostPlus;
import com.hlct.bbsservice.wxuser.WxUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApplyService {
    /**
     * 根据 post id 查找到所有申请该post 的人员
     *
     * @param postId post id
     * @return list
     */
    List<Apply> findApplyByPostId(Long postId);

    /**
     * 查找 postID 对应的apply 数量
     *
     * @return 申请该post 的 apply数量
     */
    int countByPostId(Long postId);

    /**
     * 将apply储存到数据库
     *
     * @param apply apply
     * @return apply
     */
    Apply save(Apply apply);

    /**
     * 通过openId 找到我申请的 post
     *
     * @param openId openId
     * @return apply list
     */
    List<Apply> findAllByOpenId(String openId);

    /**
     * 根据openID 来查找其申请的posts
     *
     * @param openId openId
     * @return post plus list
     */
    List<PostPlus> findApplyPostsByOpenId(String openId);

    List<ApplyUser> findApplyPersonByPostId(long postId);

    boolean hasApplied(long postId,String openId);

}
