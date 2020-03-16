package com.zdzimi.blog.service;

import com.zdzimi.blog.dao.TopbarMenuRepository;
import com.zdzimi.blog.exception.TopbarException;
import com.zdzimi.blog.model.TopbarMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopbarService {

    private TopbarMenuRepository topbarMenuRepository;

    @Autowired
    public TopbarService(TopbarMenuRepository topbarMenuRepository) {
        this.topbarMenuRepository = topbarMenuRepository;
    }

    public List<TopbarMenu> findAll(){
        return topbarMenuRepository.findAll();
    }

    public TopbarMenu findByTop(String top) {
        return topbarMenuRepository.findByTop(top).orElseThrow(() -> new TopbarException(top));
    }

    public void delete(TopbarMenu topbarMenu) {
        topbarMenuRepository.delete(topbarMenu);
    }

    public void save(TopbarMenu topbarMenu) {
        topbarMenuRepository.save(topbarMenu);
    }

    public List<String> findAllTop() {
        return topbarMenuRepository.findAll().stream()
                .map(TopbarMenu::getTop)
                .collect(Collectors.toList());
    }
}
