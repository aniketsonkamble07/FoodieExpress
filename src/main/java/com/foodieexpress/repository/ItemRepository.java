package com.foodieexpress.repository;

import com.foodieexpress.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository  extends JpaRepository<MenuItem, Long> {

    boolean existsByName(String name);
}
