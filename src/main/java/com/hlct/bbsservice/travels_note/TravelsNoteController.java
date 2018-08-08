package com.hlct.bbsservice.travels_note;

import com.hlct.bbsservice.common.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/note")
public class TravelsNoteController {

    private TravelsNoteServiceImpl travelsNoteService;
    private Logger log = LoggerFactory.getLogger(TravelsNoteController.class);

    @Autowired
    public TravelsNoteController(TravelsNoteServiceImpl travelsNoteService) {
        this.travelsNoteService = travelsNoteService;
    }
    @PostMapping(value = "/save")
    public ResultInfo<TravelsNote> save(TravelsNote travelsNote){
        ResultInfo<TravelsNote> resultInfo = new ResultInfo<>();
        TravelsNote travelsNote1 =  travelsNoteService.saveTravelNote(travelsNote);
        if (travelsNote1 != null){
            resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
            resultInfo.setMessage("发布成功");
            resultInfo.setData(travelsNote1);
        }else {
            resultInfo.setCode(ResultInfo.RESULT_ERROR);
            resultInfo.setMessage("发布失败");
        }
        return resultInfo;
    }

    public ResultInfo<List<TravelsNotePlus>> getAll(){

        return null;
    }
}
