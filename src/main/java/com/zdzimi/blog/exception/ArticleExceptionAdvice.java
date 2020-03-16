package com.zdzimi.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ArticleExceptionAdvice {

    @ExceptionHandler(ArticleException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String articleExceptionHandler(ArticleException e, Model model) {
        model.addAttribute("exception", e.getMessage());
        return "exception.jsp";
    }
}
