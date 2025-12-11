package com.foodieexpress.security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Configuration
@RequiredArgsConstructor
public class OAuth2SuccessHandler {


    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        if(authentication!=null && authentication.getPrincipal() instanceof OAuth2User)
        {
            OAuth2User oAuth2User=(OAuth2User) authentication.getPrincipal();

            String username=oAuth2User.getName();
          //  String email=oAuth2User.getEmail();
        }
    }

}
