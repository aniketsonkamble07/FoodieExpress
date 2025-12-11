package com.foodieexpress.entity;

import com.foodieexpress.enums.AuthProvider;
import com.foodieexpress.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
   private String id;
   private String username;
   private String email;

    @Enumerated(EnumType.STRING)
   private Role role;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

     private String authProviderId;
     private  String photoUrl;

}
