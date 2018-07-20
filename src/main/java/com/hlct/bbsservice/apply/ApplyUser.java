package com.hlct.bbsservice.apply;

import com.hlct.bbsservice.wxuser.WxUser;

public class ApplyUser {
    private Apply apply;
    private WxUser wxUser;

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    @Override
    public String toString() {
        return "ApplyUser{" +
                "apply=" + apply.toString() +
                ", wxUser=" + wxUser.toString() +
                '}';
    }
}
