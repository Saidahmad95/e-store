package com.example.estore.services.services_impl;

import com.example.estore.entities.Addon;
import com.example.estore.entities.Product;
import com.example.estore.payload.AddonReq;
import com.example.estore.payload.ApiResponse;
import com.example.estore.repos.AddonRepo;
import com.example.estore.services.AddonService;
import com.example.estore.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.estore.enums.ApiResponseMessages.*;
import static com.example.estore.util.CustomValidation.validateUUID;
import static com.example.estore.util.Mapper.responseEntityMaker;
import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@Service
public class AddonServiceImpl implements AddonService {

    private final AddonRepo addonRepo;
    private final ProductServiceImpl productServiceImpl;

    @Override
    public ResponseEntity<ApiResponse> addAddon(AddonReq request) {
        Optional<Product> productById = productServiceImpl.checkProductById(request.getProductUuid());
        if (productById.isPresent()) {
            Addon builtAddon = buildAddon(request, productById);
            Addon savedAddon = addonRepo.save(builtAddon);
            return responseEntityMaker(CREATED, ADDON_ADDED.getMessage(), savedAddon);
        }
        return responseEntityMaker(NOT_FOUND, ADDON_NOT_FOUND.getMessage(), null);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteAddon(String uuid) {
        Optional<Addon> addonById = checkAddonById(uuid);
        if (addonById.isPresent()) {
            Addon foundAddon = addonById.get();
            foundAddon.setDeleted(true);
            addonRepo.save(foundAddon);
            return responseEntityMaker(OK, ADDON_DELETED.getMessage(), null);
        }
        return responseEntityMaker(NOT_FOUND, ADDON_NOT_FOUND.getMessage(), null);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllAddons() {
        List<Addon> addons = addonRepo.findAll()
                .stream()
                .filter(addon -> !addon.isDeleted())
                .toList();

        return responseEntityMaker(OK,
                addons.isEmpty() ? NO_ADDONS.getMessage() : TOTAL_ADDONS.getMessage() + addons.size(),
                addons);
    }


    private static Addon buildAddon(AddonReq request, Optional<Product> productById) {
        return Addon.builder()
                .name(request.getName())
                .stock(request.getStock())
                .product(productById.get())
                .price(request.getPrice())
                .build();
    }

    public Optional<Addon> checkAddonById(String addonUuid) {
        return validateUUID(addonUuid)
                ? addonRepo.findById(fromString(addonUuid)) : Optional.empty();
    }
}
