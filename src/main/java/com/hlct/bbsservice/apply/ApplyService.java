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

    Apply findOne(long applyId);

    /**
     * 通过模板消息通知发布者 ，提示有人申请，需要审核
     * @param formId 表单Id，通过小程序获取
     * @param apply 申请表单
     * @return 是否通知成功
     */
    boolean notifyAuthor(String formId,Apply apply);

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

    /**
     * 根据posstId 获取申请人和申请列表
     * @param postId postId
     * @return list
     */
    List<ApplyUser> findApplyPersonByPostId(long postId);

    /**
     *  检查 用户（openId）是否申请了 该post
     * @param postId postId
     * @param openId wxUser 唯一Id
     * @return 是否申请了
     */
    boolean hasApplied(long postId,String openId);

    /**
     * 根据 用户id 和 post id 获取申请人列表，如果postId 对应的openId 不是传过来的openId  则说明
     *  现在的用户不是发布者，不能获取申请人信息
     * @param postId postId
     * @param openId wxUser 唯一Id
     * @return list
     */
    List<ApplyUser> getApplicants(long postId,String openId);

    List<ApplyUser> getApplicants(long postId);

    int updateStatusByOpenIdAndApplyId(String openId,long applyId,String status);

}
