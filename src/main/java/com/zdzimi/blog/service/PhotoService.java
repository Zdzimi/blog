package com.zdzimi.blog.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.zdzimi.blog.dao.PhotoRepository;
import com.zdzimi.blog.model.Paragraph;
import com.zdzimi.blog.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PhotoService {

    private PhotoRepository photoRepository;
    private ParagraphService paragraphService;
    private Cloudinary cloudinary;

    @Autowired
    public PhotoService(PhotoRepository photoRepository,
                        ParagraphService paragraphService,
                        Cloudinary cloudinary) {
        this.photoRepository = photoRepository;
        this.paragraphService = paragraphService;
        this.cloudinary = cloudinary;
    }

    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    public Photo findByIdOrNull(long photoId) {
        return photoRepository.findById(photoId).orElse(null);
    }

    public void delete(long photoId) {
        if (photoId != 0){
            Optional<Photo> photoToDelete = photoRepository.findById(photoId);
            if (photoToDelete.isPresent()){
                List<Paragraph> paragraphs = photoToDelete.get().getParagraphs();
                for (Paragraph paragraph : paragraphs) {
                    paragraph.setPhoto(null);
                    paragraphService.save(paragraph);
                }
                photoRepository.delete(photoToDelete.get());
            }
        }
    }

    public void save(String photoPath, int photoHeight, int photoWidth, String photoDescription) {
        Map result = null;
        try {
            result = cloudinary.uploader().upload(photoPath, ObjectUtils.emptyMap());
            String url = (String)result.get("url");
            String photoView = "<img src=\""+ url+"\" width=\"" + photoWidth+ "\" height=\""+ photoHeight+"\" />";
            photoRepository.save(new Photo(photoView, photoDescription));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
