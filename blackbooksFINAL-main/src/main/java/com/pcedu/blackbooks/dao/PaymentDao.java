package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Payment;
import com.pcedu.blackbooks.entities.ShoppingCart;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("paymentDao")
public class PaymentDao extends AbstractDao<Integer, Payment> {

    public Payment findById(int id) {
        Payment p = getByKey(id);
        if(p != null) {
            return p;
        }
        return null;
    }

    public List<Payment> findAll() {
         Criteria criteria = createEntityCriteria();
         return(criteria.list());
    }
    
    public List<Payment> findAllByDate(Date dateTime) {
         Criteria criteria = createEntityCriteria();
         criteria.add(Restrictions.eq("dateTime", dateTime));
         return(criteria.list());
    }
    
    public List<Payment> findAllByCart(int cartId){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("shoppingCartId").add(Restrictions.eq("id", cartId));
        return(criteria.list());
    }
    
    public boolean save(Payment payment) {
        return(persist(payment));
    }
    
    public boolean deletePayment(Payment payment) {
        if(payment != null) {
            delete(payment);
            return(true);
        }
        return(false);
    }
}
