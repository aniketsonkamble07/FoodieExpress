package com.foodieexpress.repository;

import com.foodieexpress.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    boolean existsByName(String name);
    Optional<Restaurant> findByName(String name);
    boolean existsByEmail(String email);
}
