package com.zdzimi.blog.controller.admin;

import com.zdzimi.blog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.zdzimi.blog.controller.admin.AdminNavigation.ADMIN_NAVIGATION;

@Controller
public class PhotoController {

    private PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping("/photos")
    public String showPhotoController(Model model){
        model.addAttribute("nav", ADMIN_NAVIGATION);
        model.addAttribute("photos", photoService.findAll());
        return "photo";
    }

    @RequestMapping("/delete-photo")
    public String deletePhoto(long photoId){
        photoService.delete(photoId);
        return "redirect:/photos";
    }

    @RequestMapping(value = "/add-photo", method = RequestMethod.POST)
    public String addPhoto(String photoPath, int photoHeight, int photoWidth, String photoDescription){
        photoService.save(photoPath, photoHeight, photoWidth, photoDescription);
        return "redirect:/photos";
    }
}
