package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.User;
import com.pcedu.blackbooks.entities.UserRole;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userRoleDao")
@Transactional
public class UserRoleDao  extends AbstractDao<Integer, UserRole>{

    public UserRole findById(int id) {
        UserRole u = getByKey(id);
        if (u != null) {
            return u;
        }
        return null;
    }
    
    public List<UserRole> findAll() {
        Criteria criteria = createEntityCriteria();
        return(criteria.list());
    }
    
    public List<UserRole> findByUser(User user){
        List<UserRole> userRoles = new ArrayList<>();
        for (UserRole userRole : findAll()) {
            if(userRole.getUserId().equals(user)){
                userRoles.add(userRole);
            }
        }
        return (userRoles);
    }
    
    public List<UserRole> findByRole(Role role){
        List<UserRole> userRoles = new ArrayList<>();
        for (UserRole userRole : findAll()) {
            if(userRole.getRoleId().equals(role)){
                userRoles.add(userRole);
            }
        }
        return (userRoles);
    }
    
    public boolean save(UserRole userRole){
        return(persist(userRole));
    }
    
    public boolean update(UserRole userRole) {
        if(findById(userRole.getId()) != null) return(save(userRole));
        return(false);
    }
    
    public boolean deleteUserRole(UserRole userRole){
        if(userRole != null){
            delete(userRole);
            return(true);        
        }
        return(false);
    }

}
