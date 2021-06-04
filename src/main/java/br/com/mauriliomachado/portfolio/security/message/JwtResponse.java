package br.com.mauriliomachado.portfolio.security.message;


import br.com.mauriliomachado.portfolio.model.Role;

public class JwtResponse {

    private String token;
    private Role role;
    private String type = "Bearer";

    public JwtResponse(String accessToken, Role role) {
        this.token = accessToken;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}