package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.User;
import com.pcedu.blackbooks.entities.dto.RoleDTO;
import java.util.List;

public interface IRoleService {

    public Role findById(int id);

    public RoleDTO findByIdDTO(int id);

    public List<RoleDTO> findAll();
    
    public Role findByName(String name);
    
    public List<RoleDTO> findAllByUser(User user);
    
    public boolean save(Role role);
    
    public boolean update(Role role);
    
    public boolean delete(Role role);
}
