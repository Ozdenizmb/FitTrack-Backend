package com.ozdeniz.fittrack.controller;

import com.ozdeniz.fittrack.api.AssetApi;
import com.ozdeniz.fittrack.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class AssetController implements AssetApi {

    private final AssetService service;

    @Override
    public ResponseEntity<String> uploadImage(MultipartFile file) {
        return ResponseEntity.ok(service.uploadImage(file));
    }

    @Override
    public ResponseEntity<byte[]> getImage(String fileName) {
        return ResponseEntity.ok(service.getImage(fileName));
    }

    @Override
    public ResponseEntity<Boolean> deleteImage(String fileName) {
        return ResponseEntity.ok(service.deleteImage(fileName));
    }

}
