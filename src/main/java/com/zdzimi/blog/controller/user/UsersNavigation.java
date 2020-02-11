package com.zdzimi.blog.controller.user;

import com.zdzimi.blog.dao.ChapterRepository;
import com.zdzimi.blog.dao.TopbarMenuRepository;
import com.zdzimi.blog.model.Chapter;
import com.zdzimi.blog.model.TopbarMenu;

import java.util.List;
import java.util.stream.Collectors;

public class UsersNavigation {

    static List<String> getTopMenuList(TopbarMenuRepository topbarMenuRepository) {
        return topbarMenuRepository.findAll().stream()
                .map(TopbarMenu::getTop)
                .collect(Collectors.toList());
    }

    static List<String> getChaptersList(ChapterRepository chapterRepository) {
        return chapterRepository.findAll().stream()
                .map(Chapter::getChapterTitle)
                .collect(Collectors.toList());
    }
}
