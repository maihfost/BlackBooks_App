package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Review;
import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.ShoppingCart;
import com.pcedu.blackbooks.entities.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("userDao")
@Transactional
public class UserDao extends AbstractDao<Integer, User> {
   
    public User findById(int id) {
        User u = getByKey(id);
        if(u != null) {
            return u;
        }
        return null;
    }
      
    public List<User> findByFirstLastNames(String firstName, String lastName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("firstName", firstName));
        criteria.add(Restrictions.eq("lastName", lastName));
        return(criteria.list());
    }
        
    public User findByUserName(String userName) {
        for (User user : findAll()) {
            if(user.getUserName().equals(userName)) return(user);
        }
        return(new User());
    }
    
    public Boolean existsByUsername(String userName){
        for (User user : findAll()) {
            if(user.getUserName().equals(userName)) return(true);
        }
        return (false);
    }
    
    public Boolean existsByEmail(String email){
        for (User user : findAll()) {
            if(user.getEmail().equals(email)) return(true);
        }
        return (false);
    }
    
    public List<User> findAll() {
         Criteria criteria = createEntityCriteria();
         return(criteria.list());
    }
       
    public User findActiveUser() {
        for (User user : findAll()) {
            if(user.getActive()) return(user);
        }
        return(new User());
    }
    
    public List<User> findAllByRole(Role role){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("userRoleList").add(Restrictions.eq("roleId", role));
        return (criteria.list());
    } 
    
    public User findByReview(Review review) {
        for (User user : findAll()) {
            for (Review tempReview : user.getReviewList()) {
                if(tempReview.equals(review))return(user);
            }
        }
        return(new User());
    } 

    public User findByOrder(ShoppingCart shoppingCart) {
        for (User user : findAll()) {
            for (ShoppingCart cart : user.getShoppingCartList()) {
                if(cart.equals(shoppingCart))return(user);
            }
        }
        return(new User());
    } 
    
    public boolean save(User user) {
        return(persist(user));
    }
    
    public boolean update(User user) {
        if(findByUserName(user.getUserName()) != null) return(save(user));
        return(false);
    }
    
    public boolean deleteUser(User user) {
        if(user != null) {
            delete(user);
            return(true);
        }
        return(false);
    }
}
