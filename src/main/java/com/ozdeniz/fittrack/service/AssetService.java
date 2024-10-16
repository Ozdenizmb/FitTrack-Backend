package com.ozdeniz.fittrack.service;

import org.springframework.web.multipart.MultipartFile;

public interface AssetService {

    String uploadImage(MultipartFile file);
    byte[] getImage(String fileName);
    Boolean deleteImage(String fileName);

}
