package com.foodieexpress.config;
//
//import com.foodieexpress.entity.User;
//import com.foodieexpress.enums.AuthProvider;
//import com.foodieexpress.enums.Role;
//import com.foodieexpress.repository.UserRepository;
//import com.foodieexpress.service.AuthService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.IOException;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//@Autowired
//private AuthService authService;
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/foodies/login", "/css/**", "/js/**", "/images/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth -> oauth
//                        .loginPage("/login")
//
//                        .successHandler(new AuthenticationSuccessHandler() {
//                            @Override
//                            public void onAuthenticationSuccess(HttpServletRequest request,
//                                                                HttpServletResponse response,
//                                                                Authentication authentication) throws IOException {
//
//                                // Confirm principal is OAuth2 user
//                                if (authentication instanceof OAuth2AuthenticationToken authToken) {
//
//                                    OAuth2User oAuth2User = authToken.getPrincipal();
//
//                                    // google / github / facebook
//                                    String registrationId = authToken.getAuthorizedClientRegistrationId();
//
//                                    // save or update user in DB
//                                    authService.handleOAuth2LoginRequest(oAuth2User, registrationId);
//
//                                    // redirect
//                                    response.sendRedirect("/home");
//                                }
//                            }
//                        })
//
//                        .failureHandler(new AuthenticationFailureHandler() {
//                            @Override
//                            public void onAuthenticationFailure(HttpServletRequest request,
//                                                                HttpServletResponse response,
//                                                                AuthenticationException exception) throws IOException {
//
//
//                                // Custom logic on login failure
//                                response.sendRedirect("/login?error");
//                            }
//                        })
//                )
//
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/foodies/login?logout")
//                );
//
//        return http.build();
//    }
//}
//

/*
import com.foodieexpress.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService authService;

    public SecurityConfig(AuthService authService) {
        this.authService = authService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/foodies/login", "/css/**", "/js/**", "/images/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {

                            if (authentication instanceof OAuth2AuthenticationToken authToken) {

                                OAuth2User oAuth2User = authToken.getPrincipal();
                                String registrationId =
                                        authToken.getAuthorizedClientRegistrationId();

                                authService.handleOAuth2LoginRequest(oAuth2User, registrationId);

                                response.sendRedirect("/home");
                            }
                        })
                        .failureHandler((request, response, exception) ->
                                response.sendRedirect("/login?error"))
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/foodies/login?logout")
                );

        return http.build();
    }
}
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // allow all requests without login
                )
                .csrf(csrf -> csrf.disable()) // disable CSRF temporarily
                .formLogin(form -> form.disable()) // disable login form
                .logout(logout -> logout.disable()); // disable logout

        return http.build();
    }
}
