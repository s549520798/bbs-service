package com.hlct.bbsservice.travels_note;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelsNoteRepository extends JpaRepository<TravelsNote, Long> {

    List<TravelsNote> findAllByOpenId(String openId);

    boolean existsByOpenIdAndId(String openId, Long id);

}
