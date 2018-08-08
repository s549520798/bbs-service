package com.hlct.bbsservice.travels_note;

import java.util.List;

public class NotesPage {

    private List<TravelsNotePlus> notePlusList;

    private int pageSize;

    public List<TravelsNotePlus> getNotePlusList() {
        return notePlusList;
    }

    public void setNotePlusList(List<TravelsNotePlus> notePlusList) {
        this.notePlusList = notePlusList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotlePage() {
        return totlePage;
    }

    public void setTotlePage(int totlePage) {
        this.totlePage = totlePage;
    }

    private int nextPage;

    private int totlePage;
}
