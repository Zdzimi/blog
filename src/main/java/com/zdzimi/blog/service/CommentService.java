package com.zdzimi.blog.service;

import com.zdzimi.blog.dao.CommentRepository;
import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findCommentsFromArticle(Article article) {
        return article.getComments();
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public void delete(Comment comment){
        commentRepository.delete(comment);
    }

    public void deleteCommentsByArticle(Article article) {
        List<Comment> comments = article.getComments();
        comments.forEach(this::delete);
    }

    public void deleteById(int commentId) {
        commentRepository.deleteById(commentId);
    }

    public void updateComment(int commentId, String admin, String answer) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            comment.get().setAdmin(admin);
            comment.get().setAnswer(answer);
            commentRepository.save(comment.get());
        }
    }
}
