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
}
