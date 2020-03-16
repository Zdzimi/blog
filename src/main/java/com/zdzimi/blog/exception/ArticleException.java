package com.zdzimi.blog.exception;

public class ArticleException extends RuntimeException {

    public ArticleException(String title) {
        super("Not found: " + title);
    }
}
