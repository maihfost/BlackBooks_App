package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.dto.RoleDTO;
import com.pcedu.blackbooks.services.interfaces.IRoleService;
import com.pcedu.blackbooks.services.interfaces.IUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/role"})
public class RoleRestController {

    @Autowired
    IRoleService roleService;

    @Autowired
    IUserService userService;
    
    @CrossOrigin
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public RoleDTO showRole(@PathVariable int id) {
        return(roleService.findByIdDTO(id));
    }
    
    @CrossOrigin
    @GetMapping("/ids/{ids}")
    @PreAuthorize("hasAuthority('admin')")
    public List<RoleDTO> showListRole(@PathVariable List<Integer> ids) {
        List<RoleDTO> roles = new ArrayList<>();
        for (Integer id : ids) roles.add(roleService.findByIdDTO(id));
        return (roles);
    }

    @CrossOrigin
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    public List<RoleDTO> showRoles() {
        return (roleService.findAll());
    }
    
    @CrossOrigin
    @GetMapping("/all/user/{userId}")
    @PreAuthorize("hasAuthority('admin')")
    public List<RoleDTO> showAllByRole(@PathVariable int userId){
        return(roleService.findAllByUser(userService.findById(userId)));
    } 
    
    @CrossOrigin
    @PostMapping("/new") 
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity createNewRole(@RequestBody Role role){
        roleService.save(role);
        return (new ResponseEntity(new RoleDTO(role), HttpStatus.CREATED));
    }
    
    @Transactional 
    @CrossOrigin
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity updateRole(@PathVariable int id, @RequestBody Role role){
        Role tempRole = updateCheck(role, roleService.findById(id));
        if(roleService.update(tempRole)) return (new ResponseEntity(HttpStatus.ACCEPTED));
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity deleteRole(@PathVariable int id){
        Role tempRole = roleService.findById(id);
        if(tempRole != null){
            roleService.delete(tempRole);
            return (new ResponseEntity(HttpStatus.ACCEPTED));
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    private Role updateCheck(Role role, Role tempRole){
        if(role.getName() != null) tempRole.setName(role.getName());
        return(tempRole);
    }
}
