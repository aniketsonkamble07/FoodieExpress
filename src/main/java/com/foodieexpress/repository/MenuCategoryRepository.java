package com.foodieexpress.repository;

import com.foodieexpress.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {

    boolean existsByNameAndRestaurantName(String category, String restaurantName);
    Optional<MenuCategory> findByNameAndRestaurantId(String categoryName, Long restaurantId);
    Optional<MenuCategory> findByName(String category);
}
