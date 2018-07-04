package com.hlct.bbsservice.collection;

import com.hlct.bbsservice.common.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/collection")
public class CollectionController {

    private final CollectionService service;
    private static final Logger log = LoggerFactory.getLogger(CollectionController.class);
    @Autowired
    public CollectionController(CollectionServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/save")
    public ResultInfo<Collection> save(@ModelAttribute Collection collection){
        Collection collection1 = service.save(collection);
        ResultInfo<Collection> resultInfo = new ResultInfo<>();
        if (collection1 != null){
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("收藏成功");
            resultInfo.setData(collection1);
        }else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("收藏失败");
        }
        return resultInfo;
    }
}
