package com.foodieexpress.service;

import com.foodieexpress.dto.RestaurantRegistrationRequestDTO;
import com.foodieexpress.entity.Restaurant;
import com.foodieexpress.enums.RestaurantApprovalStatus;
import com.foodieexpress.enums.RestaurantStatus;
import com.foodieexpress.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void registerRestaurant(RestaurantRegistrationRequestDTO dto) {

        if (restaurantRepository.existsByName(dto.getName())) {
            throw new IllegalStateException(
                    "Restaurant name already exists: " + dto.getName()
            );
        }

        if (restaurantRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalStateException(
                    "Restaurant email already exists: " + dto.getEmail()
            );
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setDescription(dto.getDescription());
        restaurant.setPhone(dto.getPhone());
        restaurant.setEmail(dto.getEmail());
        restaurant.setLocation(dto.getLocation());
        restaurant.setRatings(null);
        restaurant.setApprovalStatus(RestaurantApprovalStatus.PENDING);
        restaurant.setRestaurantStatus(RestaurantStatus.CLOSED);

        restaurantRepository.save(restaurant);
    }
}
