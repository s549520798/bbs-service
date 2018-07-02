package com.hlct.bbsservice.comment;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    @PostMapping(value = "/save")
    public void save(@ModelAttribute Comment comment){

    }
}
