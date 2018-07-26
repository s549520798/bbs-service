package com.hlct.bbsservice.taken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTakenRepository extends JpaRepository<AccessTaken, Long> {

    AccessTaken findTopByOrderByCreateTimeDesc();

}
