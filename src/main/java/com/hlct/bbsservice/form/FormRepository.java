package com.hlct.bbsservice.form;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface FormRepository extends JpaRepository<Form,Long> {

    List<Form> findAllByOpenId(String openId);

    Page<Form> findAll(Specification<Form> formSpecification, Pageable pageable);


}
