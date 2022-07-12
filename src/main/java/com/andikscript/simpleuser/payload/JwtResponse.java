package com.andikscript.simpleuser.payload;

import java.util.List;

public class JwtResponse {
    private String accessToken;
    private String type = "Bearer";
    private String refreshToken;
    private String username;
    private String email;
    private List roles;

    public JwtResponse(String accessToken, String refreshToken, String username, String email, List roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List getRoles() {
        return roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getType() {
        return type;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
