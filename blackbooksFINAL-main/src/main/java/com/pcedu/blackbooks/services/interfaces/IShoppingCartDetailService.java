package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import com.pcedu.blackbooks.entities.dto.ShoppingCartDetailDTO;
import java.util.List;

public interface IShoppingCartDetailService  {

    public ShoppingCartDetail findById(int id);

    public ShoppingCartDetailDTO findByIdDTO(int id);
    
    public List<ShoppingCartDetailDTO> findAll();
    
    public List<ShoppingCartDetail> findAllShoppingCart();
    
    public List<ShoppingCartDetailDTO> findAllByBook(int bookId);
    
    public List<ShoppingCartDetailDTO> findAllByShoppingCartDTO(int shoppingCartId);
    
    public List<ShoppingCartDetail> findAllByShoppingCart(int shoppingCartId);
    
    public boolean save(ShoppingCartDetail detail);
    
    public boolean update(ShoppingCartDetail detail);
    
    public boolean delete(ShoppingCartDetail detail);
}
