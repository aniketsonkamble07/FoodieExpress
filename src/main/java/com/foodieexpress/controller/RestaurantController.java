package com.foodieexpress.controller;

import com.foodieexpress.dto.RestaurantRegistrationRequestDTO;
import com.foodieexpress.dto.RestaurantRegistrationResponseDTO;
import com.foodieexpress.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/register")
    public ResponseEntity<RestaurantRegistrationResponseDTO> registerRestaurant(
            @RequestBody @Valid RestaurantRegistrationRequestDTO dto) {

        RestaurantRegistrationResponseDTO response =
                restaurantService.registerRestaurant(dto);

        if ("Success".equals(response.getMessage())) {
            return ResponseEntity.status(201).body(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
