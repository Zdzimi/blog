package com.zdzimi.blog.exception;

public class TopbarException extends RuntimeException {

    public TopbarException(String top) {
        super("Not found: " + top);
    }
}
