package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.UserRole;
import com.pcedu.blackbooks.services.interfaces.IRoleService;
import com.pcedu.blackbooks.services.interfaces.IUserRoleService;
import com.pcedu.blackbooks.services.interfaces.IUserService;
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
@RequestMapping(value = {"/api/userrole"})
public class UserRoleRestController {

    @Autowired
    IUserRoleService userRoleService;
    
    @Autowired 
    IUserService userService;
    
    @Autowired
    IRoleService roleService;
    

//    @CrossOrigin
//    @GetMapping("/user/{userId}")
//    public List<UserRole> showUserRolesByUser(@PathVariable int userId){
//        return(userRoleService.findByUser(userService.findById(userId)));
//    }
//    
//    @CrossOrigin
//    @GetMapping("/role/{roleId}")
//    public List<UserRole> showUserRolesByRole(@PathVariable int roleId){
//        return(userRoleService.findByRole(roleService.findById(roleId)));
//    }
    
    @Transactional
    @CrossOrigin
    @PostMapping("/new/{userId}/{roleId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity createNewUserRole(@PathVariable int userId, @PathVariable int roleId){
        UserRole userRole = new UserRole();
        userRole.setUserId(userService.findById(userId));
        userRole.setRoleId(roleService.findById(roleId));
        userRoleService.save(userRole);
        return (new ResponseEntity( HttpStatus.CREATED));
    }
    
    @Transactional
    @CrossOrigin
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity updateUserRole(@PathVariable int id, @RequestBody UserRole userRole){
        if(userRoleService.update(updateCheck(userRole, userRoleService.findById(id)))) return (new ResponseEntity(HttpStatus.ACCEPTED));
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity deleteUserRole(@PathVariable int id){
        UserRole userRole = userRoleService.findById(id);
        if(userRole != null){
            userRoleService.deleteUserRole(userRole);
            return (new ResponseEntity(HttpStatus.ACCEPTED));
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    private UserRole updateCheck(UserRole userRole, UserRole tempUserRole){
        if(userRole.getUserId() != null) tempUserRole.setUserId(userRole.getUserId());
        if(userRole.getRoleId() != null) tempUserRole.setRoleId(userRole.getRoleId());
        return(tempUserRole);
    }
}
