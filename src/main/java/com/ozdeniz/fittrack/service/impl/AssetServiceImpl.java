package com.ozdeniz.fittrack.service.impl;

import com.ozdeniz.fittrack.exception.ErrorMessages;
import com.ozdeniz.fittrack.exception.FittrackException;
import com.ozdeniz.fittrack.model.Asset;
import com.ozdeniz.fittrack.repository.AssetRepository;
import com.ozdeniz.fittrack.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repository;

    @Value("${file.path}")
    private String folderPath;
    @Value("${file.allowed-formats}")
    private String[] allowedFormats;
    @Value("${file.default-image-height}")
    private int defaultImageHeight;
    @Value("${file.default-image-width}")
    private int defaultImageWidth;

    @Override
    public String uploadImage(MultipartFile file) {
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String fileType = Objects.requireNonNull(file.getContentType()).split("/")[1];

        if(!Arrays.asList(allowedFormats).contains(fileType)) {
            throw FittrackException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.UNSUPPORTED_FILE_TYPE);
        }

        String fileNameAndType = fileName + "." + fileType;
        String imagePath = folderPath + fileNameAndType;

        Asset image = Asset.builder()
                .name(fileNameAndType)
                .type(file.getContentType())
                .imagePath(imagePath)
                .build();

        repository.save(image);

        try {
            BufferedImage originalImage = ImageIO.read(file.getInputStream());
            BufferedImage resizedImage = new BufferedImage(defaultImageWidth, defaultImageHeight, originalImage.getType());

            Graphics2D writer = resizedImage.createGraphics();
            writer.drawImage(originalImage, 0, 0, defaultImageWidth, defaultImageHeight, null);
            writer.dispose();

            ImageIO.write(resizedImage, fileType, new File(imagePath));
        } catch (IOException e) {
            throw FittrackException.withStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessages.FILE_CANNOT_WRITE);
        }

        return fileNameAndType;
    }

    @Override
    public byte[] getImage(String fileName) {

        Optional<Asset> response = repository.findByName(fileName);

        if(response.isPresent()) {
            String filePath = response.get().getImagePath();

            try {
                return Files.readAllBytes(new File(filePath).toPath());
            } catch (IOException e) {
                throw FittrackException.withStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessages.FILE_NOT_FOUND);
            }

        }
        else {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.FILE_NOT_FOUND);
        }

    }

    @Override
    public byte[] getImageFromUrl(String imageUrl) {
        try {
            URI uri = new URI(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == 200) {
                try (InputStream inputStream = connection.getInputStream()) {
                    return inputStream.readAllBytes();
                }
            } else {
                throw new RuntimeException("Failed to fetch image from URL: " + imageUrl);
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Error fetching image from URL: " + imageUrl, e);
        }
    }

    @Override
    public Boolean deleteImage(String fileName) {

        Optional<Asset> response = repository.findByName(fileName);

        if(response.isPresent()){
            Asset existImage = response.get();
            repository.delete(existImage);

            Path filePath = Paths.get(existImage.getImagePath());
            try {
                Files.deleteIfExists(filePath);
            } catch (Exception e) {
                throw FittrackException.withStatusAndMessage(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessages.FILE_CANNOT_DELETE);
            }

            return true;
        }
        else {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.FILE_NOT_FOUND);
        }

    }
}
