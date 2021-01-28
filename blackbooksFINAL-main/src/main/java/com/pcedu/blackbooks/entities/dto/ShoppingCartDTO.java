package com.pcedu.blackbooks.entities.dto;

import com.pcedu.blackbooks.entities.ShoppingCart;
import java.math.BigDecimal;
import java.util.Date;

public class ShoppingCartDTO {

    private Integer id;
    private Date dateTime;
    private BigDecimal totalPrice;
    private Boolean isPaid;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(ShoppingCart cart) {
        this.id = cart.getId();
        this.dateTime = cart.getDateTime();
        this.totalPrice = cart.getTotalPrice();
        this.isPaid = cart.getIsPaid();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShoppingCartDTO{id=").append(id);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", isPaid=").append(isPaid);
        sb.append('}');
        return sb.toString();
    }


}
