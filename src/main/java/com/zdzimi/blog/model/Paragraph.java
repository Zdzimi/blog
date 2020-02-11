package com.zdzimi.blog.model;

import javax.persistence.*;

@Entity
public class Paragraph {

    @Id
    private int pId;
    private String header;
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Photo photo;

    @ManyToOne
    private Article article;

    // -------------------------------------------------------------------------------------

    public Paragraph() {
    }

    public Paragraph(int pId, String header, String content, Article article) {
        this.pId = pId;
        this.header = header;
        this.content = content;
        this.article = article;
    }

    public Paragraph(int pId, String header, String content, Photo photo, Article article) {
        this.pId = pId;
        this.header = header;
        this.content = content;
        this.photo = photo;
        this.article = article;
    }

    // -------------------------------------------------------------------------------------


    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
