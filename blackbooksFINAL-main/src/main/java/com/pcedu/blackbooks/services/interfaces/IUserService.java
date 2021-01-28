package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Review;
import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.ShoppingCart;
import com.pcedu.blackbooks.entities.User;
import com.pcedu.blackbooks.entities.dto.UserDTO;
import java.util.List;

public interface IUserService {

    public User findById(int id);

    public UserDTO findByIdDTO(int id);
    
    public List<UserDTO> findByFirstLastNames(String firstName, String lastName);
    
    public UserDTO findByUserNameDTO(String userName);
    
    public User findByUserName(String userName);
    
    public List<UserDTO> findAll();
    
    public UserDTO findActiveUser();
    
    public List<UserDTO> findAllByRole(Role role);
    
    public UserDTO findAllByReview(Review review);
    
    public UserDTO findByOrder(ShoppingCart shoppingCart);
    
    public boolean save(User user);
    
    public boolean update(User user);
    
    public boolean delete(User user);
    
}
