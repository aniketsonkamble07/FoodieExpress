package com.foodieexpress.service;

import com.foodieexpress.enums.AuthProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuth2Util {

    public AuthProvider getAuthenticationProvider(String registrationId)
    {
        return switch (registrationId.toLowerCase())
        {
            case "google"->AuthProvider.GOOGLE;
            case "github"->AuthProvider.GITHUB;
            case "facebook"->AuthProvider.FACEBOOK;
            case "email"->AuthProvider.EMAIL;
            default -> throw new  IllegalArgumentException("Unsupported AuthProvider "+registrationId);

        };
    }
    public String determineProviderId(OAuth2User  oAuth2User,String registrationId)
    {
       String providerId= switch (registrationId.toLowerCase())
        {
            case "google" ->oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString();
            default -> throw new IllegalArgumentException("Unsupported AuthProvider"+ registrationId);
        };
       if(providerId==null || providerId.isBlank())
       {
           throw new IllegalArgumentException("Unsupported AuthProvider"+ registrationId);
       }
       return  providerId;
    }

    public String determineUsernameFromAuthProvider(OAuth2User oAuth2User, String registrationId , String providerId)
    {
        String email=oAuth2User.getAttribute("email");
        if(email!=null && !email.isBlank())
        {
            return  email;
        }

      return switch(registrationId)
    {
        case "google"-> oAuth2User.getAttribute("sub");
        case "github"->oAuth2User.getAttribute("login");
        default ->providerId ;
    };

    }
}
