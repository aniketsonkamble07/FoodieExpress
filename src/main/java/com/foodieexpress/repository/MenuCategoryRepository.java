package com.foodieexpress.repository;

import com.foodieexpress.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {

    boolean existsByNameAndRestaurantName(String category, String restaurantName);
}
