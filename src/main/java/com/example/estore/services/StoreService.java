package com.example.estore.services;

import com.example.estore.entities.Attachment;
import com.example.estore.entities.Store;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.StoreCreationReq;
import com.example.estore.repos.StoreRepo;
import com.example.estore.util.CustomValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;

import static com.example.estore.enums.ApiResponseMessages.*;
import static com.example.estore.util.CustomValidation.validateUUID;
import static com.example.estore.util.Mapper.apiResponseMaker;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepo storeRepo;
    private final AttachmentService attachmentService;


    public ResponseEntity<ApiResponse> createStore(StoreCreationReq request) {
        Store newStore = new Store(request.getName());
        Store savedStore = storeRepo.save(newStore);

        return ResponseEntity
                .status(CREATED)
                .body(apiResponseMaker(CREATED, STORE_CREATED.getMessage(), savedStore));
    }


    public ResponseEntity<ApiResponse> uploadAttachment(MultipartFile file, String id) throws IOException {
        Store foundStore = validateUUID(id) ? storeRepo.findById(UUID.fromString(id)).orElse(null) : null;
        if (foundStore != null) {
            HashMap<Attachment, Path> map = attachmentService.saveFile(file);
            Path path = map.values().iterator().next();
            foundStore.setAttachment(map.keySet().iterator().next());
            storeRepo.save(foundStore);
            return ResponseEntity
                    .status(CREATED)
                    .body(apiResponseMaker(CREATED, ATTACHMENT_UPLOAD.getMessage(), path));
        }
        return ResponseEntity
                .status(NOT_FOUND)
                .body(apiResponseMaker(NOT_FOUND, STORE_NOT_FOUND.getMessage(), null));
    }


}
