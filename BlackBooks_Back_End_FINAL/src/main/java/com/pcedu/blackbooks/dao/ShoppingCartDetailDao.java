package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("cartDetailDao")
public class ShoppingCartDetailDao extends AbstractDao<Integer, ShoppingCartDetail>{

    public ShoppingCartDetail findById(int id) {
        ShoppingCartDetail d = getByKey(id);
        if (d != null) {
            return d;
        }
        return null;
    }
    
    public List<ShoppingCartDetail> findAll() {
         Criteria criteria = createEntityCriteria();
         return(criteria.list());
    }

    public List<ShoppingCartDetail> findAllByBook(int bookId){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("bookId").add(Restrictions.eq("id", bookId));
        return (criteria.list());
    }
    
    public List<ShoppingCartDetail> findAllByShoppingCart(int shoppingCartId){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("shoppingCartId").add(Restrictions.eq("id", shoppingCartId));
        return (criteria.list());
    }
    
    public boolean save(ShoppingCartDetail detail){
        return(persist(detail));
    }
    
    public boolean update(ShoppingCartDetail detail) {
        if(findById(detail.getId()) != null) return(save(detail));
        return(false);
    }
    
    public boolean deleteDetail(ShoppingCartDetail detail){
        if(detail != null){
            delete(detail);
            return(true);        
        }
        return(false);
    }
}
