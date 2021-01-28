
package com.pcedu.blackbooks.security.payload.request;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class SignupRequest {
    
    //@NotBlank
    @Size(max = 100)
    private String email;

    //@NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    //@NotBlank
    @Size(min = 6, max = 60)
    private String password;
        
    //@NotBlank
    @Size(min = 3, max = 20)
    private String firstname;
    
    //@NotBlank
    @Size(min = 4, max = 100)
    private String lastname;
    
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    
    @Size(max = 100)
    private String country;
    
    @Size(min = 10, max = 15)
    private String telephone;
    
    //@NotBlank
    private BigDecimal digitwallet;
    
    private List<String> role = null;

    public List<String> getRole() {
        return role;
    }
    
    
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BigDecimal getDigitwallet() {
        return digitwallet;
    }

    public void setDigitwallet(BigDecimal digitwallet) {
        this.digitwallet = digitwallet;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
}
