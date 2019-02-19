package com.giaca.antonino.ninoeat.ui.activities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anton on 14/02/2019.
 */

public class User {
    public static final String REGISTER_ENDPOINT="auth/local/register";
    public static final String LOGIN_ENDPOINT = "auth/local";
    public static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY";

    String email;
    String password;
    String username;
    String accessToken;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public User(JSONObject user,String accessToken) throws JSONException {
        this.accessToken=accessToken;
        id=user.getString("id");
        email=user.getString("email");
        username=user.getString("username");




    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
