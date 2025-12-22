package com.foodieexpress.service;

import com.foodieexpress.dto.RestaurantRegistrationRequestDTO;
import com.foodieexpress.dto.RestaurantRegistrationResponseDTO;
import com.foodieexpress.entity.Restaurant;
import com.foodieexpress.enums.RestaurantApprovalStatus;
import com.foodieexpress.enums.RestaurantStatus;
import com.foodieexpress.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
public RestaurantRegistrationResponseDTO registerRestaurant(RestaurantRegistrationRequestDTO dto)
{

    if(restaurantRepository.existsByName(dto.getName()))
    {
      return  new RestaurantRegistrationResponseDTO("Error" , "RestaurantName already present :"+dto.getName());
    }

    if(restaurantRepository.existsByEmail(dto.getEmail()))
    {
       return new RestaurantRegistrationResponseDTO("Error" , "Restaurant name already present :"+dto.getName());
    }
    Restaurant restaurant=new Restaurant();

    restaurant.setName(dto.getName());
    restaurant.setDescription(dto.getDescription());
    restaurant.setPhone(dto.getPhone());
    restaurant.setEmail(dto.getEmail());

    restaurant.setRatings(null);
    restaurant.setApprovalStatus(RestaurantApprovalStatus.PENDING);
    restaurant.setRestaurantStatus(RestaurantStatus.CLOSED);
    restaurantRepository.save(restaurant);

   return new RestaurantRegistrationResponseDTO("Success", "Restaurant registration completed!!!");
}
}
