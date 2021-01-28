package com.pcedu.blackbooks.entities.dto;

import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import java.math.BigDecimal;

public class ShoppingCartDetailDTO {

    private Integer id;
    private BigDecimal price;
    private int quantity;

    public ShoppingCartDetailDTO() {
    }

    public ShoppingCartDetailDTO(ShoppingCartDetail detail) {
        this.id = detail.getId();
        this.price = detail.getPrice();
        this.quantity = detail.getQuantity();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShoppingCartDetailsDTO{id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }

}
