package com.zdzimi.blog;

import com.zdzimi.blog.dao.*;
import com.zdzimi.blog.model.Article;
import com.zdzimi.blog.model.Chapter;
import com.zdzimi.blog.model.Paragraph;
import com.zdzimi.blog.model.TopbarMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Controller
public class StartApplication {

    private ArticleRepository articleRepository;
    private ChapterRepository chapterRepository;
    private TopbarMenuRepository topbarMenuRepository;
    private ParagraphRepository paragraphRepository;

    @Autowired
    public StartApplication(ArticleRepository articleRepository,
                            ChapterRepository chapterRepository,
                            TopbarMenuRepository topbarMenuRepository,
                            ParagraphRepository paragraphRepository) {
        this.articleRepository = articleRepository;
        this.chapterRepository = chapterRepository;
        this.topbarMenuRepository = topbarMenuRepository;
        this.paragraphRepository = paragraphRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    protected void createData(){
        setTopbarMenu();
        createChaptersAndArticles();
    }

    private void createChaptersAndArticles() {

        for (int i = 1; i < 6; i++) {
            String chapterName = "Chapter " + i;
            Chapter chapter = new Chapter(i * 10, chapterName);
            chapterRepository.save(chapter);

            for (int j = 1; j < 5; j++) {
                String articleTittle = "Article " + j + " from " + chapterName.toLowerCase();
                String description ="Description:\nShort info about article";
                Article article = new Article(i*100 +j*10, articleTittle, description, chapter);
                articleRepository.save(article);

                for (int k = 1; k < 4; k++) {
                    String header = "Header no " + k;
                    String content = "Some random content for \n" + articleTittle + ", bla bla";
                    Paragraph paragraph = new Paragraph(i*100+j*10+k,header, content, article);
                    paragraphRepository.save(paragraph);
                }
            }
        }
    }

    private void setTopbarMenu() {
        TopbarMenu t1 = new TopbarMenu(1,"home","Welcome to my blog application...");
        TopbarMenu t2 = new TopbarMenu(2,"about us","We are bla... bla... bla... blah...");
        TopbarMenu t3 = new TopbarMenu(3,"call","555-55-55");

        this.topbarMenuRepository.save(t1);
        this.topbarMenuRepository.save(t2);
        this.topbarMenuRepository.save(t3);
    }
}
