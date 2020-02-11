package com.zdzimi.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopbarMenu {

    @Id
    private int tId;
    private String top;
    @Column(columnDefinition = "TEXT")
    private String tContent;

    // -------------------------------------------------------------------------------------

    public TopbarMenu() {
    }

    public TopbarMenu(int tId, String top, String tContent) {
        this.tId = tId;
        this.top = top;
        this.tContent = tContent;
    }

    // -------------------------------------------------------------------------------------

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }
}
