package com.example.springsocial.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();
    private final ApiBaseUrl apiBaseUrl = new ApiBaseUrl();
    private final GoogleAuthUrl googleAuthUrl = new GoogleAuthUrl();
    private final FacebookAuthUrl facebookAuthUrl = new FacebookAuthUrl();
    private final GithubAuthUrl githubAuthUrl = new GithubAuthUrl();

    public static class Auth {

        private String tokenSecret;
        private long tokenExpirationMsec;

        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpirationMsec() {
            return tokenExpirationMsec;
        }

        public void setTokenExpirationMsec(long tokenExpirationMsec) {
            this.tokenExpirationMsec = tokenExpirationMsec;
        }
    }

    public static final class OAuth2 {

        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }

    public static class ApiBaseUrl {

        private String apiBaseUrl;

        public String getApiBaseUrl() {
            return apiBaseUrl;
        }

        public void setApiBaseUrl(String apiBaseUrl) {
            this.apiBaseUrl = apiBaseUrl;
        }

    }

    public static class GoogleAuthUrl {

        private String googleAuthUrl;

        public String getGoogleAuthUrl() {
            return googleAuthUrl;
        }

        public void setGoogleAuthUrl(String googleAuthUrl) {
            this.googleAuthUrl = googleAuthUrl;
        }

    }

    public static class FacebookAuthUrl {

        private String facebookAuthUrl;

        public String getFacebookAuthUrl() {
            return facebookAuthUrl;
        }

        public void setFacebookAuthUrl(String facebookAuthUrl) {
            this.facebookAuthUrl = facebookAuthUrl;
        }

    }

    public static class GithubAuthUrl {

        private String githubAuthUrl;

        public String getGithubAuthUrl() {
            return githubAuthUrl;
        }

        public void setGithubAuthUrl(String githubAuthUrl) {
            this.githubAuthUrl = githubAuthUrl;
        }

    }

    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }

    public ApiBaseUrl getApiBaseUrl() {
        return apiBaseUrl;
    }

    public GoogleAuthUrl getGoogleAuthUrl() {
        return googleAuthUrl;
    }

    public FacebookAuthUrl getFacebookAuthUrl() {
        return facebookAuthUrl;
    }

    public GithubAuthUrl getGithubAuthUrl() {
        return githubAuthUrl;
    }

}
