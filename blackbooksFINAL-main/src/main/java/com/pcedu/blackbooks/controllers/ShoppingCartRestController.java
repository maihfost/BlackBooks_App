package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.ShoppingCart;
import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import com.pcedu.blackbooks.entities.dto.ShoppingCartDTO;
import com.pcedu.blackbooks.services.UserDetailsImpl;
import com.pcedu.blackbooks.services.interfaces.IBookService;
import com.pcedu.blackbooks.services.interfaces.IPaymentService;
import com.pcedu.blackbooks.services.interfaces.IShoppingCartDetailService;
import com.pcedu.blackbooks.services.interfaces.IShoppingCartService;
import com.pcedu.blackbooks.services.interfaces.IUserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping(value = {"/api/cart"})
public class ShoppingCartRestController {

    @Autowired
    IShoppingCartService cartService;
    
    @Autowired
    IShoppingCartDetailService detailService;
    
    @Autowired
    IPaymentService paymentService;
    
    @Autowired
    IBookService bookService;
    
    @Autowired 
    IUserService userService;
    
    //UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //int userIdd = userDetails.getId();
    
    @CrossOrigin
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('user')")
    public ShoppingCartDTO showShoppingCart(@PathVariable int id) {
        return(cartService.findByIdDTO(id));
    }
    
    @CrossOrigin
    @GetMapping("/ids/{ids}")
    @PreAuthorize("hasAuthority('admin')")
    public List<ShoppingCartDTO> showListShoppingCart(@PathVariable List<Integer> ids) {
        List<ShoppingCartDTO> carts = new ArrayList<>();
        for (Integer id : ids) carts.add(cartService.findByIdDTO(id));
        return (carts);
    }
    
    @CrossOrigin
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    public List<ShoppingCartDTO> showShoppingCarts() {
        return (cartService.findAll());
    }
    
    @CrossOrigin
    @GetMapping("/all/user/{userId}")
    @PreAuthorize("hasAuthority('user')")
    public List<ShoppingCartDTO> showShoppingCartsByUser(@PathVariable int userId) {
        return (cartService.findAllByUser(userId));
    }
    
    @CrossOrigin
    @GetMapping("/cartdetail/{cartDetailId}")
    @PreAuthorize("hasAuthority('admin')")
    public ShoppingCartDTO showShoppingCartByShoppingCartDetail(@PathVariable int cartDetailId){
        return(cartService.findByShoppingCartDetail(detailService.findById(cartDetailId)));
    }
    
    @CrossOrigin
    @GetMapping("/payment/{paymentId}")
    @PreAuthorize("hasAuthority('user')")
    public ShoppingCartDTO showShoppingCartByPayment(@PathVariable int paymentId){
        return(cartService.findByPayment(paymentService.findById(paymentId)));
    }
    
    @Transactional
    @CrossOrigin
    @PostMapping("/addtocart/{cartId}/{bookId}/{quantity}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity addItemToCart(@PathVariable int bookId, @PathVariable int quantity, @PathVariable  int cartId){
        Book book = bookService.findById(bookId);
        if(book.getStoreQuantity() >= quantity){
            if(cartId == 0) cartId = cartFill(SecurityContextHolder.getContext().getAuthentication().getName());
            return(detailFill(book, cartId, quantity));
        }
        return(new ResponseEntity( HttpStatus.NOT_ACCEPTABLE));
    }
    
    @Transactional
    @CrossOrigin
    @PostMapping("/admin/addtocart/{cartId}/{bookId}/{quantity}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity adminAddItemToCart(@PathVariable int bookId, @PathVariable int quantity, @PathVariable  int cartId){
        Book book = bookService.findById(bookId);
        if(book.getStoreQuantity() >= quantity){
            return(detailFill(book, cartId, quantity));
        }
        return(new ResponseEntity( HttpStatus.NOT_ACCEPTABLE));
    }
    
    private int cartFill(String username){
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(userService.findByUserName(username));
        cartService.save(cart);
        List<ShoppingCart> carts = cartService.findAllByUserDesc(userService.findByUserName(username).getId());
        return(carts.get(0).getId());        
    }
    
    private ResponseEntity detailFill(Book book, int cartId, int quantity){
        ShoppingCart cart = cartService.findById(cartId);
        ShoppingCartDetail detail = new ShoppingCartDetail();
        detail.setBookId(book);
        detail.setQuantity(quantity);
        detail.setPrice(book.getRegularPrice());
        detail.setShoppingCartId(cart);
        detailService.save(detail);
        return(new ResponseEntity(new ShoppingCartDTO(cart), HttpStatus.ACCEPTED));
    }
    
    @CrossOrigin
    @DeleteMapping("/deletefromcart/{cartDetailId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity deleteItemFromCart (@PathVariable int cartDetailId){
        ShoppingCartDetail detail = detailService.findById(cartDetailId);
        if(detail != null){
            detailService.delete(detail);
            return (new ResponseEntity(HttpStatus.ACCEPTED));
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    @Transactional
    @CrossOrigin
    @PutMapping("/updatecart/{cartDetailId}/{quantity}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity updateItemFromCart(@PathVariable int cartDetailId, @PathVariable int quantity){
        ShoppingCartDetail detail = detailService.findById(cartDetailId);
        if(quantity == 0) {
            if(detail != null){
                detailService.delete(detail);
                return (new ResponseEntity(HttpStatus.ACCEPTED));
            }
        }
        detail.setQuantity(quantity);
        if(detailService.update(detail)) return (new ResponseEntity(HttpStatus.ACCEPTED));
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));    
    }
        
    @Transactional
    @CrossOrigin
    @PostMapping("/submitorder/{cartId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity submitOrder(@PathVariable  int cartId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username != null){
            ShoppingCart cart = cartService.findById(cartId);
            //check if user's wallet has enough money
            if(cart.getTotalPrice().compareTo(userService.findByUserName(username).getDigitWallet()) <= 0){
                cart.setDateTime(new Date());
                cart.setIsPaid(Boolean.FALSE);
                cartService.update(cart);
                return(new ResponseEntity(new ShoppingCartDTO(cart), HttpStatus.ACCEPTED));
            }
            return(new ResponseEntity(HttpStatus.NOT_ACCEPTABLE));
        }
        return(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
  
    @CrossOrigin
    @DeleteMapping("/delete/{cartId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity deleteOrder (@PathVariable int cartId){
        ShoppingCart tempCart = cartService.findById(cartId);
        if(tempCart != null){
            cartService.delete(tempCart);
            return (new ResponseEntity(HttpStatus.ACCEPTED));
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
}