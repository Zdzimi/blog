package com.zdzimi.blog.controller.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class CloudinaryConnection {

    private static String cloudName;
    private static String apiKey;
    private static String apiSecret;

    static {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream("cloudinary.properties"));
            cloudName = properties.getProperty("cloud_name");
            apiKey = properties.getProperty("api_key");
            apiSecret = properties.getProperty("api_secret");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public Cloudinary cloudinary(){
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
        return cloudinary;
    }
}
