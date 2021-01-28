package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.dto.ShoppingCartDetailDTO;
import com.pcedu.blackbooks.services.interfaces.IShoppingCartDetailService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/cartdetail"})
public class ShoppingCartDetailRestController {

    @Autowired
    IShoppingCartDetailService cartDetailService;

    @CrossOrigin
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ShoppingCartDetailDTO showShoppingCartDetail(@PathVariable int id) {
        return(cartDetailService.findByIdDTO(id));
    }
    
    @CrossOrigin
    @GetMapping("/ids/{ids}")
    @PreAuthorize("hasAuthority('admin')")
    public List<ShoppingCartDetailDTO> showListShoppingCartDetail(@PathVariable List<Integer> ids) {
        List<ShoppingCartDetailDTO> cartDetails = new ArrayList<>();
        for (Integer id : ids) cartDetails.add(cartDetailService.findByIdDTO(id));
        return (cartDetails);
    }
    
    @CrossOrigin
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    public List<ShoppingCartDetailDTO> showShoppingCartDetails() {
        return (cartDetailService.findAll());
    }
    
    @CrossOrigin
    @GetMapping("/all/book/{bookId}")
    @PreAuthorize("hasAuthority('admin')")
    public List<ShoppingCartDetailDTO> showShoppingCartDetailsByBookId(@PathVariable int bookId) {
        return (cartDetailService.findAllByBook(bookId));
    }
    
    @CrossOrigin
    @GetMapping("/all/shoppingcart/{shoppingCartId}")
    @PreAuthorize("hasAuthority('user')")
    public List<ShoppingCartDetailDTO> showShoppingCartdetailsByShoppingCartId(@PathVariable int shoppingCartId) {
        return (cartDetailService.findAllByShoppingCartDTO(shoppingCartId));
    }
}
