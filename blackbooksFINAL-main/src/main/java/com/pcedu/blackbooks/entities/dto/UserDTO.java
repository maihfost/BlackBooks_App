package com.pcedu.blackbooks.entities.dto;

import com.pcedu.blackbooks.entities.User;
import java.math.BigDecimal;
import java.util.Date;

public class UserDTO {

    private Integer id;
    private Boolean active;
    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateofbirth;
    private String country;
    private String telephone;
    private BigDecimal totalPaymentAmount;
    private BigDecimal digitWallet;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.active = user.getActive();
        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateofbirth = user.getDateofbirth();
        this.country = user.getCountry();
        this.telephone = user.getTelephone();
        this.totalPaymentAmount = user.getTotalPaymentAmount();
        this.digitWallet = user.getDigitWallet();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public BigDecimal getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    public BigDecimal getDigitWallet() {
        return digitWallet;
    }

    public void setDigitWallet(BigDecimal digitWallet) {
        this.digitWallet = digitWallet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserDTO{id=").append(id);
        sb.append(", active=").append(active);
        sb.append(", email=").append(email);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", dateofbirth=").append(dateofbirth);
        sb.append(", country=").append(country);
        sb.append(", telephone=").append(telephone);
        sb.append(", totalPaymentAmount=").append(totalPaymentAmount);
        sb.append(", digitWallet=").append(digitWallet);
        sb.append('}');
        return sb.toString();
    }

}
