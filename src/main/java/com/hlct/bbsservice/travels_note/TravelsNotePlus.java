package com.hlct.bbsservice.travels_note;

import com.hlct.bbsservice.wxuser.WxUser;

public class TravelsNotePlus {

    private TravelsNote travelsNote;
    private WxUser wxUser;

    public TravelsNote getTravelsNote() {
        return travelsNote;
    }

    public void setTravelsNote(TravelsNote travelsNote) {
        this.travelsNote = travelsNote;
    }

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }
}
