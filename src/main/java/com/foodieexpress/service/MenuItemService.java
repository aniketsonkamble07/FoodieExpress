package com.foodieexpress.service;

import com.foodieexpress.dto.ItemRequestDTO;
import com.foodieexpress.entity.MenuCategory;
import com.foodieexpress.entity.MenuItem;
import com.foodieexpress.exceptions.MenuItemAlreadyPresentException;
import com.foodieexpress.exceptions.ResourceNotFoundException;
import com.foodieexpress.repository.ItemRepository;
import com.foodieexpress.repository.MenuCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MenuItemService {

    private final ItemRepository itemRepository;
    private final MenuCategoryRepository categoryRepository;

    public MenuItemService(ItemRepository itemRepository,
                           MenuCategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    public void addMenuItem(ItemRequestDTO dto) {

        if (itemRepository.existsByName(dto.getName())) {
            throw new MenuItemAlreadyPresentException(
                    "Menu item already present: " + dto.getName()
            );
        }


        // Get the restaurant id using jwt token
        MenuCategory category = categoryRepository
                .findByName(dto.getCategoryName())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Menu category not found: " + dto.getCategoryName()
                        )
                );

        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setCategory(category);
        item.setImage(dto.getImage());
        item.setItemStatus(dto.getItemStatus());
        item.setRegisterTime(LocalDateTime.now());

        itemRepository.save(item);
    }
}
