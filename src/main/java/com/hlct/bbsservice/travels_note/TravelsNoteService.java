package com.hlct.bbsservice.travels_note;

import com.hlct.bbsservice.common.ResultPage;

import java.util.List;

public interface TravelsNoteService {
    /**
     * 根据 游记 实体类 储存到数据库
     * @param travelsNote travel note
     * @return 数据库对象
     */
    TravelsNote saveTravelNote(TravelsNote travelsNote);

    /**
     * 根据Id 找到 travel note
     * @param id id
     * @return 数据库结果
     */
    TravelsNote findById(Long id);

    /**
     * 根据 发布人id 找到其所有的游记
     * @param openId openid
     * @return list
     */
    List<TravelsNote> findAllByOpenId(String openId);

    List<TravelsNotePlus> findAll();

    ResultPage<TravelsNotePlus> findNotesByPage(int page);

}
