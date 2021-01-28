package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.UserRoleDao;
import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.User;
import com.pcedu.blackbooks.entities.UserRole;
import com.pcedu.blackbooks.services.interfaces.IUserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userRoleService")
@Transactional
public class UserRoleService implements IUserRoleService{

    @Autowired
    UserRoleDao userRoleDao;
    
    @Override
    public UserRole findById(int id) {
        return(userRoleDao.findById(id));
    }
    
    public List<UserRole> findByUser(User user){
        return(userRoleDao.findByUser(user));
    }
    
    public List<UserRole> findByRole(Role role){
        return(userRoleDao.findByRole(role));
    }
    
    public boolean save(UserRole userRole){
        boolean result = false;
        result = userRoleDao.save(userRole);
        return (result);
    }
    
    public boolean update(UserRole userRole) {
        boolean result = false;
        result = userRoleDao.update(userRole);
        return (result);
    }
    
    public boolean deleteUserRole(UserRole userRole){
        boolean result = false;
        result = userRoleDao.deleteUserRole(userRole);
        return (result);
    }

}
