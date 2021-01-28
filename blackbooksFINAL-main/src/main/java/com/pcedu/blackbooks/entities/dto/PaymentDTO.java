package com.pcedu.blackbooks.entities.dto;

import com.pcedu.blackbooks.entities.Payment;
import java.util.Date;

public class PaymentDTO {

    private Integer id;
    private Date dateTime;

    public PaymentDTO() {
    }

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.dateTime = payment.getDateTime();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaymentDTO{id=").append(id);
        sb.append(", dateTime=").append(dateTime);
        sb.append('}');
        return sb.toString();
    }


}
