package com.foodieexpress.service;

import com.foodieexpress.dto.MenuCategoryRequestDTO;
import com.foodieexpress.entity.MenuCategory;
import com.foodieexpress.entity.Restaurant;
import com.foodieexpress.exceptions.CategoryAlreadyPresentException;
import com.foodieexpress.exceptions.ResourceNotFoundException;
import com.foodieexpress.repository.MenuCategoryRepository;
import com.foodieexpress.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuCategoryService {

    private final MenuCategoryRepository repository;
    private final RestaurantRepository restaurantRepository;

    public MenuCategoryService(MenuCategoryRepository repository,
                               RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    public void addMenuCategory(MenuCategoryRequestDTO dto) {

        if (repository.existsByNameAndRestaurantName(
                dto.getName(), dto.getRestaurant_name())) {
            throw new CategoryAlreadyPresentException(
                    "Menu category already exists for this restaurant"
            );
        }

        Restaurant restaurant = restaurantRepository
                .findByName(dto.getRestaurant_name())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Restaurant not found: " + dto.getRestaurant_name()
                        )
                );

        MenuCategory category = new MenuCategory();
        category.setName(dto.getName());
        category.setRestaurant(restaurant);

        repository.save(category);
    }
}
