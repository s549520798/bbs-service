package com.hlct.bbsservice.apply;

import java.util.List;

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
     * @return  申请该post 的 apply数量
     */
    int countByPostId(Long postId);

    /**
     * 将apply储存到数据库
     * @param apply apply
     * @return apply
     */
    Apply save(Apply apply);

    List<Apply> findAllByOpenId(String openId);
}
