package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.TopbarMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopbarMenuRepository extends JpaRepository <TopbarMenu, Integer> {

    Optional<TopbarMenu> findByTop(String top);
}
