package com.spring5.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
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
public class FacebookController {

    private static final Logger log = LoggerFactory.getLogger(FacebookController.class);

    // Creates a facebook connection using the given application id and secret key.
    private FacebookConnectionFactory factory = new FacebookConnectionFactory("833523870340274", "7301de3c93a2cf9d53fbd210ffa358e3");

    // Index page.
//    @GetMapping(value = "/")
//    public ModelAndView index() {
//        return new ModelAndView("welcome");
//    }
    // Redirection uri.
    @GetMapping(value = "/facebooklogin")
    public String redirect() {
        // Creates the OAuth2.0 flow and performs the oauth handshake on behalf of the user.
        OAuth2Operations operations = factory.getOAuthOperations();

        // Builds the OAuth2.0 authorize url and the scope parameters.
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/forwardLogin");
        params.setScope("email, public_profile");

        // Url to redirect the user for authentication via OAuth2.0 authorization code grant.
        String authUrl = operations.buildAuthenticateUrl(params);
        log.info("redirect to {}" + authUrl);
        return "redirect:" + authUrl;
    }

    // Welcome page.
    @GetMapping(value = "/forwardLogin")
    public ModelAndView prodducer(@RequestParam("code") String authorizationCode) {
        // Creates the OAuth2.0 flow and performs the oauth handshake on behalf of the user.
        OAuth2Operations operations = factory.getOAuthOperations();

        // OAuth2.0 access token.
        // "exchangeForAccess()" method exchanges the authorization code for an access grant.
        AccessGrant accessToken = operations.exchangeForAccess(authorizationCode, "http://localhost:8080/forwardLogin", null);

        Connection<Facebook> connection = factory.createConnection(accessToken);

        // Getting the connection that the current user has with facebook.
        Facebook facebook = connection.getApi();
        // Fetching the details from the facebook.
        String[] fields = {"id", "name", "email", "about", "birthday"};
        User userProfile = facebook.fetchObject("me", User.class, fields);
        printUserprofile(userProfile);

        ModelAndView model = new ModelAndView("fbuserdetails");
        model.addObject("user", userProfile);
        return model;
    }

    private void printUserprofile(User user) {
        log.info("profile name {} email {}, birthday {} about {}", user.getName(), user.getEmail(), user.getBirthday(), user.getAbout());
    }
}
