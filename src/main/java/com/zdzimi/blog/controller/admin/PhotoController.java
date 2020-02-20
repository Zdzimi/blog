package com.zdzimi.blog.controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.zdzimi.blog.dao.ParagraphRepository;
import com.zdzimi.blog.dao.PhotoRepository;
import com.zdzimi.blog.model.Paragraph;
import com.zdzimi.blog.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class PhotoController {

    private PhotoRepository photoRepository;
    private ParagraphRepository paragraphRepository;
    private Cloudinary cloudinary;

    @Autowired
    public PhotoController(PhotoRepository photoRepository,
                           ParagraphRepository paragraphRepository,
                           Cloudinary cloudinary) {
        this.photoRepository = photoRepository;
        this.paragraphRepository = paragraphRepository;
        this.cloudinary = cloudinary;
    }

    @RequestMapping("/photos")
    public String showPhotoController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("photos", photoRepository.findAll());
        return "photo";
    }

    @RequestMapping("/delete-photo")
    public String deletePhoto(long photoId){
        if (photoId == 0) return "redirect:/photos";
        Optional<Photo> photoToDelete = photoRepository.findById(photoId);
        if (photoToDelete.isPresent()){
            Set<Paragraph> paragraphs = photoToDelete.get().getParagraphs();
            for (Paragraph paragraph : paragraphs) {
                paragraph.setPhoto(null);
                paragraphRepository.save(paragraph);
            }
            photoRepository.delete(photoToDelete.get());
        }
        return "redirect:/photos";
    }

    @RequestMapping(value = "/add-photo", method = RequestMethod.POST)
    public String addPhoto(String photoPath, int photoHeight, int photoWidth, String photoDescription){
        Map result = null;
        try {
            result = cloudinary.uploader().upload(photoPath, ObjectUtils.emptyMap());
            String url = (String)result.get("url");
            String photoView = "<img src=\""+ url+"\" width=\"" + photoWidth+ "\" height=\""+ photoHeight+"\" />";
            photoRepository.save(new Photo(photoView, photoDescription));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/photos";
    }
}
