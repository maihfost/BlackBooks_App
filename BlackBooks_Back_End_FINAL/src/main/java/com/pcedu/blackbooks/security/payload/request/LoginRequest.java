
package com.pcedu.blackbooks.security.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    //@NotBlank
    private String username;
    //@NotBlank
    private String Password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return Password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    
    
    
    
}
