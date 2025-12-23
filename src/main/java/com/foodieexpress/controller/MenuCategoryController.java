package com.foodieexpress.controller;

import com.foodieexpress.dto.ApiResponse;
import com.foodieexpress.dto.MenuCategoryRequestDTO;
import com.foodieexpress.service.MenuCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/restaurants")
public class MenuCategoryController {

    private final MenuCategoryService service;

    public MenuCategoryController(MenuCategoryService service) {
        this.service = service;
    }

    @PostMapping("/addmenucategory")
    public ResponseEntity<ApiResponse<Void>> addMenuCategory(
            @Valid @RequestBody MenuCategoryRequestDTO dto) {

        service.addMenuCategory(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Void>builder()
                        .success(true)
                        .message("Menu category added successfully")
                        .timestamp(LocalDateTime.now())
                        .build());
    }

}
