package com.zdzimi.blog.exception;

public class ChapterExcepion extends RuntimeException {

    public ChapterExcepion(String title) {
        super("Not found: " + title);
    }
}
