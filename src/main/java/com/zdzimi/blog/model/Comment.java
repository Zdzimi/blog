package com.zdzimi.blog.model;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;
    private String username;
    @Column(columnDefinition = "TEXT")
    private String commentContent;
    private String admin;
    @Column(columnDefinition = "TEXT")
    private String answer;

    @ManyToOne
    private Article article;

    // -------------------------------------------------------------------------------------

    public Comment() {
    }

    public Comment(String username, String commentContent, Article article) {
        this.username = username;
        this.commentContent = commentContent;
        this.article = article;
    }

    // -------------------------------------------------------------------------------------

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
