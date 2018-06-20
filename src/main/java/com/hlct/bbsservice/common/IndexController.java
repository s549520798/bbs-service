package com.hlct.bbsservice.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index controller
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping
    public String Index(){
        return "index";
    }

}
