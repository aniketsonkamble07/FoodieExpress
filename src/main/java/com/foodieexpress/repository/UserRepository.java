package com.foodieexpress.repository;

import com.foodieexpress.entity.User;
import com.foodieexpress.enums.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByAuthProviderIdAndAuthProvider(
            String authProviderId,
            AuthProvider authProvider
    );
}

