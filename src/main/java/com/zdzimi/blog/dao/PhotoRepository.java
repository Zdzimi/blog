package com.zdzimi.blog.dao;

import com.zdzimi.blog.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository <Photo, Long> {
}
