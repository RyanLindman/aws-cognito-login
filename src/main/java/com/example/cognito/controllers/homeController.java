package com.example.cognito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDeleteUserRequest;


@Controller
public class homeController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;


    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;



    @GetMapping("/")
    public String getHomePage(Authentication auth, Model model) {

        if (auth == null) {
            model.addAttribute("loggedIn", false);
        } else {
            var username = auth.getName();

            model.addAttribute("loggedIn", true);
            model.addAttribute("username", username);
        }

        return "home";
    }

    @GetMapping("/logged-in")
    public String getLoginSuccess(OAuth2AuthenticationToken authentication) {

        authentication.getName();
        authentication.getPrincipal().getAttribute("sub");


        return "redirect:/";
    }

    @GetMapping("/delete-success")
    public String getDeleteSuccess(AdminDeleteUserRequest request){


        return "redirect:/";

    }
}
