package com.foodieexpress.service;

import com.foodieexpress.entity.User;
import com.foodieexpress.enums.AuthProvider;
import com.foodieexpress.enums.Role;
import com.foodieexpress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AuthService {
@Autowired
private UserRepository repository;

@Autowired
private  OAuth2Util oAuth2Util;

    public ResponseEntity<?> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {

        // Extract OAuth2 attributes
        String providerId = oAuth2Util.determineProviderId(oAuth2User, registrationId);
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        String picture = oAuth2User.getAttribute("picture");
        AuthProvider authProvider = oAuth2Util.getAuthenticationProvider(registrationId);

        // Check if user exists by providerId+provider
        User user = repository.findUser(providerId, authProvider);

        // Check if email matches an existing account from different provider
        User userEmail = repository.findByEmail(email);

        String username = oAuth2Util.determineUsernameFromAuthProvider(oAuth2User, registrationId, providerId);

        // -------------------------
        // 1. BRAND NEW USER (Signup)
        // -------------------------
        if (user == null && userEmail == null) {

            User newUser = new User();
            newUser.setId(providerId);               // provider unique id
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPhotoUrl(picture);
            newUser.setRole(Role.USER);
            newUser.setAuthProvider(authProvider);
            newUser.setAuthProviderId(providerId);

            user = repository.save(newUser);         // IMPORTANT
        }

        // -------------------------
        // 2. EXISTING USER (Login)
        // -------------------------
        else if (user != null) {

            // Rare: email changed on provider side (Google/GitHub)
            if (email != null && !email.isBlank() && !email.equals(user.getEmail())) {
                user.setEmail(email);
                user.setUsername(username);
                repository.save(user);
            }
        }

        // -------------------------
        // 3. CONFLICT (Different Provider)
        // -------------------------
        else {
            throw new BadCredentialsException(
                    "This email is already registered with provider: " + userEmail.getAuthProvider()
            );
        }

        return ResponseEntity.ok(user);
    }

}
