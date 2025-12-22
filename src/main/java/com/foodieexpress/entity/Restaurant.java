package com.foodieexpress.entity;

import com.foodieexpress.enums.RestaurantApprovalStatus;
import com.foodieexpress.enums.RestaurantStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false,length = 15)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantRating> ratings;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantApprovalStatus approvalStatus;

    /* Operational status */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestaurantStatus restaurantStatus;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuCategory> categories;

}
