package com.foodieexpress.config;

import com.foodieexpress.service.OAuth2Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OAuth2Util oAuth2Util() {
        return new OAuth2Util();
    }
}