package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.PaymentDao;
import com.pcedu.blackbooks.entities.Payment;
import com.pcedu.blackbooks.entities.dto.PaymentDTO;
import com.pcedu.blackbooks.services.interfaces.IPaymentService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("paymentService")
@Transactional
public class PaymentService implements IPaymentService{

    @Autowired
    PaymentDao paymentDao;

    @Override
    public Payment findById(int id){
        return(paymentDao.findById(id));
    }

    @Override
    public PaymentDTO findByIdDTO(int id){
        Payment payment = findById(id);
        return(new PaymentDTO(payment));
    } 
    
    @Override
    public List<PaymentDTO> findAll(){
        List<Payment> payments = paymentDao.findAll();
        List<PaymentDTO> paymentDTOs = new ArrayList<>();
        for (Payment payment : payments) paymentDTOs.add(new PaymentDTO(payment));
        return(paymentDTOs);
    }
    
    @Override
    public List<PaymentDTO> findAllByDate(Date dateTime) {
        List<Payment> payments = paymentDao.findAllByDate(dateTime);
        List<PaymentDTO> paymentDTOs = new ArrayList<>();
        for (Payment payment : payments) paymentDTOs.add(new PaymentDTO(payment));
        return(paymentDTOs);
    }
    
    @Override
    public List<PaymentDTO> findAllByCart(int cartId){
        List<Payment> payments = paymentDao.findAllByCart(cartId);
        List<PaymentDTO> paymentDTOs = new ArrayList<>();
        for (Payment payment : payments) paymentDTOs.add(new PaymentDTO(payment));
        return(paymentDTOs);
    }
    
    @Override
    public boolean save(Payment payment){
        boolean result = false;
        result = paymentDao.save(payment);
        return (result);
    }
    
    @Override
    public boolean delete(Payment payment){
        boolean result = false;
        result = paymentDao.deletePayment(payment);
        return (result);
    }
}
