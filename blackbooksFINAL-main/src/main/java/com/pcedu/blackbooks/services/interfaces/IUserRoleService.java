package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.User;
import com.pcedu.blackbooks.entities.UserRole;
import java.util.List;

public interface IUserRoleService {

    public UserRole findById(int id);
    
    public List<UserRole> findByUser(User user);
    
    public List<UserRole> findByRole(Role role);
    
    public boolean save(UserRole userRole);
    
    public boolean update(UserRole userRole);
    
    public boolean deleteUserRole(UserRole userRole);
}
