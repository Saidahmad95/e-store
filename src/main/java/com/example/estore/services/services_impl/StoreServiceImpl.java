package com.example.estore.services.services_impl;

import com.example.estore.entities.Attachment;
import com.example.estore.entities.Store;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.StoreReq;
import com.example.estore.repos.StoreRepo;
import com.example.estore.services.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.estore.enums.ApiResponseMessages.*;
import static com.example.estore.util.CustomValidation.validateUUID;
import static com.example.estore.util.Mapper.apiResponseMaker;
import static com.example.estore.util.Mapper.responseEntityMaker;
import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepo storeRepo;
    private final AttachmentService attachmentService;


    public ApiResponse<Store> createStore(StoreReq request) {
        Store newStore = new Store(request.getName());
        Store savedStore = storeRepo.save(newStore);

        return apiResponseMaker(CREATED, STORE_CREATED.getMessage(), savedStore);
    }


    public ApiResponse<Path> uploadAttachment(MultipartFile file, String id) throws IOException {
        Store foundStore = validateUUID(id) ? storeRepo.findById(UUID.fromString(id)).orElse(null) : null;
        if (foundStore != null) {
            HashMap<Attachment, Path> map = attachmentService.saveFile(file);
            Path path = map.values().iterator().next();
            foundStore.setAttachment(map.keySet().iterator().next());
            storeRepo.save(foundStore);
            return apiResponseMaker(CREATED, ATTACHMENT_UPLOAD.getMessage(), path);
        }
        return apiResponseMaker(NOT_FOUND, STORE_NOT_FOUND.getMessage());
    }

    @Override
    public ApiResponse<Store> editStore(String id, StoreReq request) {
        Store foundStore = validateUUID(id) ? storeRepo.findById(UUID.fromString(id)).orElse(null) : null;
        if (foundStore != null) {
            foundStore.setName(request.getName());
            Store savedStore = storeRepo.save(foundStore);
            return apiResponseMaker(OK, STORE_UPDATED.getMessage(), savedStore);
        }
        return apiResponseMaker(NOT_FOUND, STORE_NOT_FOUND.getMessage());
    }

    @Override
    public ApiResponse<List<Store>> getAllStores() {
        List<Store> storeList = storeRepo.findAll();
        return apiResponseMaker(
                OK,
                storeList.isEmpty() ? NO_STORES.getMessage() : TOTAL_STORES.getMessage() + storeList.size(),
                storeList);
    }

    public Optional<Store> checkStoreById(String uuid) {
        return validateUUID(uuid)
                ? storeRepo.findById(fromString(uuid)) : Optional.empty();
    }


}
