package com.zdzimi.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Chapter {

    @Id
    private int chapterId;
    @Column(unique = true)
    private String chapterTitle;

    @OneToMany(mappedBy = "chapter")
    private Set<Article> articles;

    // -------------------------------------------------------------------------------------

    public Chapter() {
    }

    public Chapter(int chapterId, String chapterTitle) {
        this.chapterId = chapterId;
        this.chapterTitle = chapterTitle;
    }

    // -------------------------------------------------------------------------------------

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
