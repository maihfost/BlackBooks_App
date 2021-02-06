package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.ShoppingCart;
import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import com.pcedu.blackbooks.entities.dto.UserDTO;
import com.pcedu.blackbooks.services.interfaces.IShoppingCartDetailService;
import com.pcedu.blackbooks.services.interfaces.IShoppingCartService;
import com.pcedu.blackbooks.services.interfaces.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = {"/api/home"})
public class HomeRestController {
    
    @Autowired
    IUserService userService;
    
    @Autowired
    IShoppingCartDetailService detailService;
    
    @Autowired
    IShoppingCartService cartService;

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity home(ModelMap view) {
        return (new ResponseEntity(HttpStatus.OK));
    }
    
    @Transactional
    @CrossOrigin
    @DeleteMapping("/signout")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity signOut(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //find user's last cart to delete if did not purchase order
        List<ShoppingCart> carts = cartService.findAllByUserDesc(userService.findByUserName(username).getId());
        //if he didn't sumbit order date is null!
        if(carts.get(0).getDateTime() == null){
             // find details with cartId carts.get(0)
            for (ShoppingCartDetail detail : detailService.findAllByShoppingCart(carts.get(0).getId())){
                detailService.delete(detail);
            }
            cartService.delete(carts.get(0));
        }
        return(new ResponseEntity(HttpStatus.OK));
    }
}
//"Origin: http://127.0.0.1:5501",