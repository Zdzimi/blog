package com.zdzimi.blog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long photoId;
    private String photoView;
    private String photoDescription;

    @OneToMany(mappedBy = "photo")
    private List<Paragraph> paragraphs = new ArrayList<>();

    //---------------------------------------------------------------------------------------------

    public Photo() {
    }

    public Photo(String photoView, String photoDescription) {
        this.photoView = photoView;
        this.photoDescription = photoDescription;
    }

    //---------------------------------------------------------------------------------------------

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoView() {
        return photoView;
    }

    public void setPhotoView(String photoView) {
        this.photoView = photoView;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
