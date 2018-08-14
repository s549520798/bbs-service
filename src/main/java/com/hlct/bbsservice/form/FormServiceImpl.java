package com.hlct.bbsservice.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;

import java.util.Date;

@Service
public class FormServiceImpl implements FormService {

    private FormRepository formRepository;

    private Logger logger = LoggerFactory.getLogger(FormServiceImpl.class);
    @Autowired
    public FormServiceImpl(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Override
    public Form saveFormId(Form form) {
        return formRepository.save(form);
    }

    @Override
    public Form getValidFormId(String openId) {
        Date sevenDaysAgo = new Date(new Date().getTime() - 1000 * 604000); //比七天之前的时间 多了800秒
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "saveDate"));
        PageRequest pageRequest = PageRequest.of(0, 10, sort);
        Page<Form> page = formRepository.findAll((Specification<Form>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("openId").as(String.class),openId));
            predicate.getExpressions().add(criteriaBuilder
                    .greaterThanOrEqualTo(root.get("saveDate").as(Date.class),sevenDaysAgo));
            return predicate;
        },pageRequest);
        if (page.getContent().size() > 0 ){
            return page.getContent().get(0);
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteInvalidFormId(Form form) {
        if (!formRepository.existsById(form.getId())){
            return false;
        }else {
            formRepository.delete(form);
            return true;
        }
    }
}
