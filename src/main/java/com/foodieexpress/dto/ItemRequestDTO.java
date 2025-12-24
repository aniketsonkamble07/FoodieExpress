package com.foodieexpress.dto;

import com.foodieexpress.entity.MenuCategory;
import com.foodieexpress.enums.ItemStatus;
import jakarta.validation.constraints.NotBlank;

public class ItemRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Double price;

    private ItemStatus itemStatus;
    private  String image;
    @NotBlank
    private MenuCategory menuCategory;

}
