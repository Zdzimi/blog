package com.zdzimi.blog.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Article {

    @Id
    private int articleId;
    private String articleTitle;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "article")
    private Set<Paragraph> paragraphs;

    @OneToMany(mappedBy = "article")
    private Set<Comment>comments;

    @ManyToOne
    private Chapter chapter;

    // -------------------------------------------------------------------------------------

    public Article() {
    }

    public Article(int articleId, String articleTitle, String description, Chapter chapter) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.description = description;
        this.chapter = chapter;
    }

    // -------------------------------------------------------------------------------------


    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(Set<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
