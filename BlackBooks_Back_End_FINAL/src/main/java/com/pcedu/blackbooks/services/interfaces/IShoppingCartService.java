package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Payment;
import com.pcedu.blackbooks.entities.ShoppingCart;
import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import com.pcedu.blackbooks.entities.dto.ShoppingCartDTO;
import java.util.List;

public interface IShoppingCartService {

    public ShoppingCart findById(int id);

    public ShoppingCartDTO findByIdDTO(int id);
    
    public List<ShoppingCartDTO> findAll();

    public List<ShoppingCartDTO> findAllByUser(int userId);
    
    public List<ShoppingCart> findAllByUserDesc(int userId);
    
    public ShoppingCartDTO findByShoppingCartDetail(ShoppingCartDetail detail);
    
    public ShoppingCartDTO findByPayment(Payment payment);
    
    public boolean save(ShoppingCart cart);
    
    public boolean update(ShoppingCart cart);
    
    public boolean delete(ShoppingCart cart);
}
