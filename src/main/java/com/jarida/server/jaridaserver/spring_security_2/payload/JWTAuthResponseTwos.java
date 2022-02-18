package com.jarida.server.jaridaserver.spring_security_2.payload;

public class JWTAuthResponseTwos {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long expiryDuration;

    public JWTAuthResponseTwos(String accessToken, Long expiryDuration ) {
        this.accessToken = accessToken;
        this.expiryDuration = expiryDuration;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType){
        this.tokenType = tokenType;
    }

    public String getAccessTokenType() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiryDuration() {
        return expiryDuration;
    }
}
