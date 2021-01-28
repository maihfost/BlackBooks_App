package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Payment;
import com.pcedu.blackbooks.entities.ShoppingCart;
import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("shoppingCartDao")
public class ShoppingCartDao extends AbstractDao<Integer, ShoppingCart> {

    public ShoppingCart findById(int id) {
        ShoppingCart c = getByKey(id);
        if(c != null) {
            return c;
        }
        return null;
    }

    public List<ShoppingCart> findAll() {
         Criteria criteria = createEntityCriteria();
         return(criteria.list());
    }
    
    public List<ShoppingCart> findAllByUser(int userId){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("userId").add(Restrictions.eq("id", userId));
        return (criteria.list());
    }
    
    public List<ShoppingCart> findAllByUserDesc(int userId){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("userId").add(Restrictions.eq("id", userId));
        criteria.addOrder(Order.desc("id"));
        return (criteria.list());
    }
    
    public ShoppingCart findByShoppingCartDetail(ShoppingCartDetail detail){
        for (ShoppingCart cart : findAll()) {
            for (ShoppingCartDetail tempDetail : cart.getShoppingCartDetailList()) {
                if(tempDetail.equals(detail))return(cart);
            }
        }
        return(new ShoppingCart());
    }
    
    public ShoppingCart findByPayment(Payment payment){
        for (ShoppingCart cart : findAll()) {
            for (Payment tempPayment : cart.getPaymentList()) {
                if(tempPayment.equals(payment))return(cart);
            }
        }
        return(new ShoppingCart());
    }
    
    public boolean save(ShoppingCart cart){
        return(persist(cart));
    }
    
    public boolean update(ShoppingCart cart) {
        if(findById(cart.getId()) != null) return(save(cart));
        return(false);
    }
    
    public boolean deleteShoppingCart(ShoppingCart cart){
        if(cart != null){
            delete(cart);
            return(true);        
        }
        return(false);
    }
}
