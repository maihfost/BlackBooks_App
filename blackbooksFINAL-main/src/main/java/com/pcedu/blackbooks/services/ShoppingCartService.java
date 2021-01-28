package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.ShoppingCartDao;
import com.pcedu.blackbooks.entities.Payment;
import com.pcedu.blackbooks.entities.ShoppingCart;
import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import com.pcedu.blackbooks.entities.dto.ShoppingCartDTO;
import com.pcedu.blackbooks.services.interfaces.IShoppingCartService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("shoppingCartService")
@Transactional
public class ShoppingCartService implements IShoppingCartService{

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart findById(int id){
        return(shoppingCartDao.findById(id));
    }

    @Override
    public ShoppingCartDTO findByIdDTO(int id){
        ShoppingCart cart = findById(id);
        return(new ShoppingCartDTO(cart));
    }
    
    @Override
    public List<ShoppingCartDTO> findAll(){
        List<ShoppingCart> carts = shoppingCartDao.findAll();
        List<ShoppingCartDTO> cartDTOs = new ArrayList<>();
        for (ShoppingCart cart : carts) {
            cartDTOs.add(new ShoppingCartDTO(cart));
        }
        return(cartDTOs);
    }

    @Override
    public List<ShoppingCartDTO> findAllByUser(int userId){
        List<ShoppingCart> carts = shoppingCartDao.findAllByUser(userId);
        List<ShoppingCartDTO> cartDTOs = new ArrayList<>();
        for (ShoppingCart cart : carts) {
            cartDTOs.add(new ShoppingCartDTO(cart));
        }
        return(cartDTOs);
    }
    
    @Override
    public List<ShoppingCart> findAllByUserDesc(int userId){
        return(shoppingCartDao.findAllByUserDesc(userId));
    }
    
    @Override
    public ShoppingCartDTO findByShoppingCartDetail(ShoppingCartDetail detail){
        ShoppingCart cart = shoppingCartDao.findByShoppingCartDetail(detail);
        return(new ShoppingCartDTO(cart));
    }
    
    @Override
    public ShoppingCartDTO findByPayment(Payment payment){
        ShoppingCart cart = shoppingCartDao.findByPayment(payment);
        return(new ShoppingCartDTO(cart));
    }
    
    @Override
    public boolean save(ShoppingCart cart) {
        boolean result = false;
        result = shoppingCartDao.save(cart);
        return (result);
    }
    
    @Override
    public boolean update(ShoppingCart cart) {
        boolean result = false;
        result = shoppingCartDao.update(cart);
        return (result);
    }
    
    @Override
    public boolean delete(ShoppingCart cart){
        boolean result = false;
        result = shoppingCartDao.deleteShoppingCart(cart);
        return (result);
    }
}
