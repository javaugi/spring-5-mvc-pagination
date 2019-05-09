/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.example.springsocial.model;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class AuthParams {

    private static final Logger log = LoggerFactory.getLogger(AuthParams.class);
    private String apiBaseUrl;
    private String googleAuthUrl;
    private String facebookAuthUrl;
    private String githubAuthUrl;
    private String authTokenSecret;
    private List<String> auth2AuthorizedRedirectUris;

    public AuthParams() {
    }

    public AuthParams(String apiBaseUrl, String googleAuthUrl, String facebookAuthUrl, String githubAuthUrl) {
        this.apiBaseUrl = apiBaseUrl;
        this.googleAuthUrl = googleAuthUrl;
        this.facebookAuthUrl = facebookAuthUrl;
        this.githubAuthUrl = githubAuthUrl;
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public void setApiBaseUrl(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    public String getGoogleAuthUrl() {
        return googleAuthUrl;
    }

    public void setGoogleAuthUrl(String googleAuthUrl) {
        this.googleAuthUrl = googleAuthUrl;
    }

    public String getFacebookAuthUrl() {
        return facebookAuthUrl;
    }

    public void setFacebookAuthUrl(String facebookAuthUrl) {
        this.facebookAuthUrl = facebookAuthUrl;
    }

    public String getGithubAuthUrl() {
        return githubAuthUrl;
    }

    public void setGithubAuthUrl(String githubAuthUrl) {
        this.githubAuthUrl = githubAuthUrl;
    }

    public String getAuthTokenSecret() {
        return authTokenSecret;
    }

    public void setAuthTokenSecret(String authTokenSecret) {
        this.authTokenSecret = authTokenSecret;
    }

    public List<String> getAuth2AuthorizedRedirectUris() {
        return auth2AuthorizedRedirectUris;
    }

    public void setAuth2AuthorizedRedirectUris(List<String> auth2AuthorizedRedirectUris) {
        this.auth2AuthorizedRedirectUris = auth2AuthorizedRedirectUris;
    }

    @Override
    public String toString() {
        return "AuthParams{" + "apiBaseUrl=" + apiBaseUrl + ", googleAuthUrl=" + googleAuthUrl + ", facebookAuthUrl=" + facebookAuthUrl + ", githubAuthUrl=" + githubAuthUrl + ", authTokenSecret=" + authTokenSecret + ", auth2AuthorizedRedirectUris=" + auth2AuthorizedRedirectUris + '}';
    }

}
