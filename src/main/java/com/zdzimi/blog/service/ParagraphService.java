package com.zdzimi.blog.service;

import com.zdzimi.blog.dao.ParagraphRepository;
import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParagraphService {

    private ParagraphRepository paragraphRepository;

    @Autowired
    public ParagraphService(ParagraphRepository paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    public List<Paragraph> findAll() {
        return paragraphRepository.findAll();
    }

    public List<Paragraph> findParagraphsFromArticle(Article article) {
        return article.getParagraphs();
    }

    public void delete(Paragraph paragraph) {
        paragraphRepository.delete(paragraph);
    }

    public void deleteParagraphsByAtricle(Article article) {
        List<Paragraph> paragraphs = article.getParagraphs();
        paragraphs.forEach(this::delete);
    }

    public void deleteById(int pId) {
        Optional<Paragraph> paragraph = paragraphRepository.findById(pId);
        paragraph.ifPresent(p -> paragraphRepository.delete(p));
    }

    public void save(Paragraph paragraph) {
        paragraphRepository.save(paragraph);
    }
}
