package com.example.springsocial.controller;

import com.example.springsocial.config.AppProperties;
import com.example.springsocial.exception.BadRequestException;
import com.example.springsocial.model.AuthParams;
import com.example.springsocial.model.AuthProvider;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.ApiResponse;
import com.example.springsocial.payload.AuthResponse;
import com.example.springsocial.payload.LoginRequest;
import com.example.springsocial.payload.SignUpRequest;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    //*
    @GetMapping(value = "/sociallogin")
    //@ModelAttribute("authParams")
    public String redirect() {
        AuthParams authParams = new AuthParams(appProperties.getApiBaseUrl().getApiBaseUrl(),
                appProperties.getGoogleAuthUrl().getGoogleAuthUrl(), appProperties.getFacebookAuthUrl().getFacebookAuthUrl(),
                appProperties.getGithubAuthUrl().getGithubAuthUrl());
        ModelAndView model = new ModelAndView("sociallogin");
        model.addObject("authParams", authParams);
        log.debug("go to socila login authParams {}", authParams);
        return "sociallogin";
    }

    // */
    //*
    @GetMapping(value = "/sociallogin")
    public ModelAndView sociallogin() {
        ModelAndView model = new ModelAndView("sociallogin");
        AuthParams authParams = new AuthParams(appProperties.getApiBaseUrl().getApiBaseUrl(),
                appProperties.getGoogleAuthUrl().getGoogleAuthUrl(), appProperties.getFacebookAuthUrl().getFacebookAuthUrl(),
                appProperties.getGithubAuthUrl().getGithubAuthUrl());
        log.debug("go to socila login authParams {}", authParams);
        model.addObject("authParams", authParams);
        return model;
    }

    // */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.debug("authenticateUser {}", loginRequest);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        //return ResponseEntity.ok(new AuthResponse(token));

        AuthResponse response = new AuthResponse(token);
        ResponseEntity responseEntity = ResponseEntity.ok(response);
        log.debug("token {} authReponse {} return ()", token, response, responseEntity);
        return responseEntity;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}
