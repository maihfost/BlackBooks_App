package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.ShoppingCartDetailDao;
import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import com.pcedu.blackbooks.entities.dto.ShoppingCartDetailDTO;
import com.pcedu.blackbooks.services.interfaces.IShoppingCartDetailService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cartDetailService")
@Transactional
public class ShoppingCartDetailService  implements IShoppingCartDetailService{

    @Autowired
    ShoppingCartDetailDao cartDetailDao; 
    
    @Override
    public ShoppingCartDetail findById(int id) {
        return cartDetailDao.findById(id);
    }

    @Override
    public ShoppingCartDetailDTO findByIdDTO(int id) {
        ShoppingCartDetail detail = findById(id);
        return (new ShoppingCartDetailDTO(detail));
    }
    
    @Override
    public List<ShoppingCartDetailDTO> findAll() {
        List<ShoppingCartDetail> details = cartDetailDao.findAll();
        List<ShoppingCartDetailDTO> detailDTOs = new ArrayList<>();
        for (ShoppingCartDetail detail : details) {
            detailDTOs.add(new ShoppingCartDetailDTO(detail));
        }
        return(detailDTOs);
    }
    
    @Override
    public List<ShoppingCartDetail> findAllShoppingCart() {
        return(cartDetailDao.findAll());
    }
    
    @Override
    public List<ShoppingCartDetailDTO> findAllByBook(int bookId){
        List<ShoppingCartDetail> details = cartDetailDao.findAllByBook(bookId);
        List<ShoppingCartDetailDTO> detailDTOs = new ArrayList<>();
        for (ShoppingCartDetail detail : details) {
            detailDTOs.add(new ShoppingCartDetailDTO(detail));
        }
        return(detailDTOs);
    }
    
    @Override
    public List<ShoppingCartDetailDTO> findAllByShoppingCartDTO(int shoppingCartId){
        List<ShoppingCartDetail> details = cartDetailDao.findAllByShoppingCart(shoppingCartId);
        List<ShoppingCartDetailDTO> detailDTOs = new ArrayList<>();
        for (ShoppingCartDetail detail : details) {
            detailDTOs.add(new ShoppingCartDetailDTO(detail));
        }
        return(detailDTOs);
    }
    
    @Override
    public List<ShoppingCartDetail> findAllByShoppingCart(int shoppingCartId){
        return(cartDetailDao.findAllByShoppingCart(shoppingCartId));
    }
    
    @Override
    public boolean save(ShoppingCartDetail detail) {
        boolean result = false;
        result = cartDetailDao.save(detail);
        return (result);
    }
    
    @Override
    public boolean update(ShoppingCartDetail detail) {
        boolean result = false;
        result = cartDetailDao.update(detail);
        return (result);
    }
    
    @Override
    public boolean delete(ShoppingCartDetail detail){
        boolean result = false;
        result = cartDetailDao.deleteDetail(detail);
        return (result);
    }
}
