package com.foodieexpress.controller;

import com.foodieexpress.dto.ApiResponse;
import com.foodieexpress.dto.ItemRequestDTO;
import com.foodieexpress.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/restaurant/")
public class ItemController {

    private final MenuItemService service;
    public  ItemController(MenuItemService service)
    {
        this.service=service;

    }

    @PostMapping("/addmenuitem")
    public ResponseEntity<ApiResponse<Void>> addMenuItem(
            @Valid @RequestBody ItemRequestDTO dto)
    {
        service.addMenuItem(dto);

       return ResponseEntity.status(HttpStatus.CREATED)
               .body(ApiResponse.<Void>builder()
               .success(true)
                       .message("Menu item added successfully")
                       .timestamp(LocalDateTime.now())
                       .build());
    }

}
