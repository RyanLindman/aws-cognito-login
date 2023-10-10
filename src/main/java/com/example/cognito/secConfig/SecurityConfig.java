package com.example.cognito.secConfig;

import com.example.cognito.secConfig.CognitoLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;



    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {


                http.authorizeHttpRequests()
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated();



                http.oauth2Login().defaultSuccessUrl("/logged-in");

                var logoutSuccessHandler = new CognitoLogoutSuccessHandler(clientRegistrationRepository);


                http.logout()
                        .logoutSuccessHandler(logoutSuccessHandler);

                return http.build();

    }
}



