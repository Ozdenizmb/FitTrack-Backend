package com.ozdeniz.fittrack.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ImageDirectoryManager {

    @Value("${file.path}")
    private String imagePath;

    @PostConstruct
    public void init() {
        File directory = new File(imagePath);

        if (!directory.exists()) {
            boolean mkdirs = directory.mkdirs();
        }
    }

}
