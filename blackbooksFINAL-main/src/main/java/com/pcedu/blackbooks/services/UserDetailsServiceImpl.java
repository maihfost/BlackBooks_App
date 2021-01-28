/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.UserDao;
import com.pcedu.blackbooks.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maihf
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    UserDao userRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//try{
            
            User user = userRepository.findByUserName(username);
            System.out.println("Did loadUserByUserNameFounduser");
            System.out.println(user.toString());
            
            return UserDetailsImpl.build(user);
            
        //}catch (UsernameNotFoundException e){
            //System.out.println("User Not Found with username:" + username);
        //}
        
                                    //User user = userRepository.findByUserName(username);
				//.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

	}
}
