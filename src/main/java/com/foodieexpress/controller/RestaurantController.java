package com.foodieexpress.controller;

import com.foodieexpress.dto.ApiResponse;
import com.foodieexpress.dto.RestaurantRegistrationRequestDTO;
import com.foodieexpress.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> registerRestaurant(
            @Valid @RequestBody RestaurantRegistrationRequestDTO dto) {

        restaurantService.registerRestaurant(dto);

        return ResponseEntity.status(201)
                .body(ApiResponse.<Void>builder()
                        .success(true)
                        .message("Restaurant registered successfully")
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
