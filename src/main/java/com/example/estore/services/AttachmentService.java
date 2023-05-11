package com.example.estore.services;

import com.example.estore.entities.Attachment;
import com.example.estore.repos.AttachmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepo attachmentRepo;
    private final String FOLDER_PATH = "C:/MINE/JAVA WEB FULL_STACK DEV/SPRING/PRACTICE/e-store/src/main/resources/uploads/";

    public HashMap<Attachment,Path> saveFile(MultipartFile file) throws IOException {
        Attachment builtAttachment = Attachment
                .builder()
                .name(file.getOriginalFilename())
                .contentType(file.getContentType())
                .fileSize(file.getSize())
                .build();
        Attachment savedAttachment = attachmentRepo.save(builtAttachment);
        byte[] bytes = file.getBytes();
        Path path = Paths.get(FOLDER_PATH + file.getOriginalFilename());
        Path writtenPath = Files.write(path, bytes);
        HashMap<Attachment, Path> attachmentAndPath = new HashMap<>();
        attachmentAndPath.put(savedAttachment,writtenPath);
        return attachmentAndPath ;
    }
}
