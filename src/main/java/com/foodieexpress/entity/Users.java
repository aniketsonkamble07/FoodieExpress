package com.foodieexpress.entity;

import com.foodieexpress.enums.AuthProvider;
import com.foodieexpress.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity

public class Users {
    @Id
   private Long id;
   private String username;
   private String password;

    @Enumerated(EnumType.STRING)
   private Role role;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

     private String authProviderId;

}
