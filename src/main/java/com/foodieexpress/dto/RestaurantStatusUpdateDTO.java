package com.foodieexpress.dto;

import com.foodieexpress.enums.RestaurantStatus;
import jakarta.validation.constraints.NotNull;

public class RestaurantStatusUpdateDTO {
    @NotNull
    private RestaurantStatus restaurantStatus;
}
