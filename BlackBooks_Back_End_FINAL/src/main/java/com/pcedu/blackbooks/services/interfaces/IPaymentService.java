package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Payment;
import com.pcedu.blackbooks.entities.dto.PaymentDTO;
import java.util.Date;
import java.util.List;

public interface IPaymentService {

    public Payment findById(int id);

    public PaymentDTO findByIdDTO(int id);
    
    public List<PaymentDTO> findAll();
    
    public List<PaymentDTO> findAllByDate(Date dateTime);
    
    public List<PaymentDTO> findAllByCart(int cartId);
    
    public boolean save(Payment payment);
    
    public boolean delete(Payment payment);
}
