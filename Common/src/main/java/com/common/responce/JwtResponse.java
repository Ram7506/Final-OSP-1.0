package com.common.responce;

public class JwtResponse {

    private String jwt;

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "jwt='" + jwt + '\'' +
                '}';
    }

    public JwtResponse() {
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}

