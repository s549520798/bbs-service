package com.hlct.bbsservice.travels_note;

import com.hlct.bbsservice.common.ResultPage;
import com.hlct.bbsservice.wxuser.WxUser;
import com.hlct.bbsservice.wxuser.WxUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelsNoteServiceImpl implements TravelsNoteService {

    private TravelsNoteRepository repository;
    private WxUserRepository wxUserRepository;

    @Autowired
    public TravelsNoteServiceImpl(TravelsNoteRepository repository, WxUserRepository wxUserRepository) {
        this.repository = repository;
        this.wxUserRepository = wxUserRepository;
    }

    @Override
    public TravelsNote saveTravelNote(TravelsNote travelsNote) {
        return repository.save(travelsNote);
    }

    @Override
    public TravelsNote findById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<TravelsNote> findAllByOpenId(String openId) {
        return repository.findAllByOpenId(openId);
    }

    @Override
    public List<TravelsNotePlus> findAll() {
        List<TravelsNotePlus> list = new ArrayList<>();
        List<TravelsNote> travelsNotes = repository.findAll();
        for (TravelsNote note : travelsNotes) {
            TravelsNotePlus travelsNotePlus = new TravelsNotePlus();
            travelsNotePlus.setTravelsNote(note);
            WxUser wxUser = wxUserRepository.findWxUserByOpenId(note.getOpenId());
            travelsNotePlus.setWxUser(wxUser);
            list.add(travelsNotePlus);
        }

        return list;
    }

    @Override
    public ResultPage<TravelsNotePlus> findNotesByPage(int page) {
        ResultPage<TravelsNotePlus> resultPage = new ResultPage<>();

        int pageSize = 10;
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "createTime"));
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        Page<TravelsNote> travelsNotePage = repository.findAll(pageRequest);

        resultPage.setCurrentPage(page);
        resultPage.setPageSize(pageSize);
        resultPage.setTotalPage(travelsNotePage.getTotalPages());
        resultPage.setLast(travelsNotePage.isLast());
        resultPage.setNextPage(travelsNotePage.hasNext() ? travelsNotePage.nextPageable().getPageNumber() : -1);
        List<TravelsNotePlus> travelsNotePluses = new ArrayList<>();
        for (TravelsNote travelsNote : travelsNotePage.getContent()){
            TravelsNotePlus travelsNotePlus = new TravelsNotePlus();
            WxUser wxUser = wxUserRepository.findWxUserByOpenId(travelsNote.getOpenId());
            travelsNotePlus.setWxUser(wxUser);
            travelsNotePlus.setTravelsNote(travelsNote);
            travelsNotePluses.add(travelsNotePlus);
        }
        resultPage.setContent(travelsNotePluses);
        return resultPage;
    }
}
