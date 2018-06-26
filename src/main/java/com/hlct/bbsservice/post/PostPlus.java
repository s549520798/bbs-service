package com.hlct.bbsservice.post;

import com.hlct.bbsservice.wxuser.WxUser;

/**
 * 发帖首页返回的文章列表，包含帖子的基本信息，发帖人信息，申请状态等信息
 */
public class PostPlus {

    private Post post;
    private WxUser wxUser;
    private boolean isCalling;


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public boolean isCalling() {
        return isCalling;
    }

    public void setCalling(boolean calling) {
        isCalling = calling;
    }
}
