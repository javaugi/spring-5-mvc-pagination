package com.spring5.controller.users;

import com.example.springsocial.config.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.User;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * https://examples.javacodegeeks.com/enterprise-java/spring/boot/spring-boot-social-login-example/?utm_source=sendpulse&utm_medium=push&utm_campaign=1279706
 * https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/
 * https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-2/
 * https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-3/
 *
 */
@Controller
public class GoogleController {

    private static final Logger log = LoggerFactory.getLogger(GoogleController.class);

    // Creates a facebook connection using the given application id and secret key.
    //private FacebookConnectionFactory factory = new FacebookConnectionFactory("833523870340274", "7301de3c93a2cf9d53fbd210ffa358e3");
    private GoogleConnectionFactory factory = new GoogleConnectionFactory("5014057553-8gm9um6vnli3cle5rgigcdjpdrid14m9.apps.googleusercontent.com", "tWZKVLxaD_ARWsriiiUFYoIk");

    @Autowired
    private AppProperties appProperties;

    // Index page.
//    @GetMapping(value = "/")
//    public ModelAndView index() {
//        return new ModelAndView("welcome");
//    }
    // Redirection uri.
    @GetMapping(value = "/googlelogin")
    public String redirect() {
        // Creates the OAuth2.0 flow and performs the oauth handshake on behalf of the user.
        OAuth2Operations operations = factory.getOAuthOperations();

        // Builds the OAuth2.0 authorize url and the scope parameters.
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/forwardGoogleLogin");
        params.setScope("email, public_profile");

        // Url to redirect the user for authentication via OAuth2.0 authorization code grant.
        String authUrl = operations.buildAuthenticateUrl(params);
        log.debug("redirect to {}" + authUrl);
        return "redirect:" + authUrl;
    }

    // Welcome page.
    @GetMapping(value = "/forwardGoogleLogin")
    public ModelAndView prodducer(@RequestParam("code") String authorizationCode) {
        // Creates the OAuth2.0 flow and performs the oauth handshake on behalf of the user.
        OAuth2Operations operations = factory.getOAuthOperations();

        // OAuth2.0 access token.
        // "exchangeForAccess()" method exchanges the authorization code for an access grant.
        AccessGrant accessToken = operations.exchangeForAccess(authorizationCode, "http://localhost:8080/forwardGoogleLogin", null);

        Connection<Google> connection = factory.createConnection(accessToken);

        // Getting the connection that the current user has with facebook.
        UserProfile profile = connection.fetchUserProfile();
        User user = new User(profile.getId(), profile.getName(), profile.getFirstName(), profile.getLastName(), null, null);
        printUserprofile(user);

        ModelAndView model = new ModelAndView("fbuserdetails");
        model.addObject("user", user);
        return model;
    }

    private void printUserprofile(User user) {
        log.debug("profile name {} email {}, birthday {} about {}", user.getName(), user.getEmail(), user.getBirthday(), user.getAbout());
    }
}
