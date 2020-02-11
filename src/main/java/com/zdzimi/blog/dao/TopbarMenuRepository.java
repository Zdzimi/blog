package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.TopbarMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopbarMenuRepository extends JpaRepository <TopbarMenu, Integer> {

    TopbarMenu findByTop(String top);
}
