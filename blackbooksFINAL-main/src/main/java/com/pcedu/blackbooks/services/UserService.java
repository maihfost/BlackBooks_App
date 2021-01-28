package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.UserDao;
import com.pcedu.blackbooks.entities.Review;
import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.ShoppingCart;
import com.pcedu.blackbooks.entities.User;
import com.pcedu.blackbooks.entities.dto.UserDTO;
import com.pcedu.blackbooks.services.interfaces.IUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService implements IUserService{

    @Autowired
    UserDao userDao;

    @Override
    public User findById(int id){
        return(userDao.findById(id));
    }

    @Override
    public UserDTO findByIdDTO(int id){
        User user = findById(id);
        return(new UserDTO(user));
    } 
    
    @Override
    public List<UserDTO> findByFirstLastNames(String firstName, String lastName){
        List<User> users = userDao.findByFirstLastNames(firstName, lastName);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user));
        }
        return(userDTOs);
    }
    
    @Override
    public UserDTO findByUserNameDTO(String userName){
        User user = userDao.findByUserName(userName);
        return(new UserDTO(user));
    }
    
    @Override
    public User findByUserName(String userName){
        return(userDao.findByUserName(userName));
    }
    
    @Override
    public List<UserDTO> findAll(){
        List<User> users = userDao.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user));
        }
        return(userDTOs);
    }
    
    @Override
    public UserDTO findActiveUser(){
        User user = userDao.findActiveUser();
        return(new UserDTO(user));
    }
    
    @Override
    public List<UserDTO> findAllByRole(Role role){
        List<User> users = userDao.findAllByRole(role);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user));
        }
        return(userDTOs);
    }
    
    @Override
    public UserDTO findAllByReview(Review review) {
        User user = userDao.findByReview(review);
        return(new UserDTO(user));
    }
    
    @Override
    public UserDTO findByOrder(ShoppingCart shoppingCart){
        User user = userDao.findByOrder(shoppingCart);
        return(new UserDTO(user));
    }
    
    @Override
    public boolean save(User user) {
        boolean result = false;
        result = userDao.save(user);
        return (result);
    }
    
    @Override
    public boolean update(User user) {
        boolean result = false;
        result = userDao.update(user);
        return (result);
    }
    
    @Override
    public boolean delete(User user){
        boolean result = false;
        result = userDao.deleteUser(user);
        return (result);
    }
}
