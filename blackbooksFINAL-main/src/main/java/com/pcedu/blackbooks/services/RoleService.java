package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.RoleDao;
import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.User;
import com.pcedu.blackbooks.entities.dto.RoleDTO;
import com.pcedu.blackbooks.services.interfaces.IRoleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional
public class RoleService implements IRoleService{

    @Autowired 
    RoleDao roleDao;
    
    @Override
    public Role findById(int id){
        return(roleDao.findById(id));
    }

    @Override
    public RoleDTO findByIdDTO(int id){
        Role role = findById(id);
        return(new RoleDTO(role));
    } 

    @Override
    public List<RoleDTO> findAll(){
        List<Role> roles = roleDao.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for (Role role : roles) roleDTOs.add(new RoleDTO(role));
        return(roleDTOs);
    }
    
    public Role findByName(String name) {
        return(roleDao.findByName(name));
    }
    
    @Override
    public List<RoleDTO> findAllByUser(User user){
        List<Role> roles = roleDao.findAllByUser(user);
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for (Role role : roles) roleDTOs.add(new RoleDTO(role));
        return(roleDTOs);
    }
    
    @Override
    public boolean save(Role role) {
        boolean result = false;
        result = roleDao.save(role);
        return (result);
    }
    
    @Override
    public boolean update(Role role) {
        boolean result = false;
        result = roleDao.update(role);
        return (result);
    }
    
    @Override
    public boolean delete(Role role){
        boolean result = false;
        result = roleDao.deleteRole(role);
        return (result);
    }
}
