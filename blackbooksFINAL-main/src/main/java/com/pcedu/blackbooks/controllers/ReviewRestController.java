package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.Review;
import com.pcedu.blackbooks.entities.dto.ReviewDTO;
import com.pcedu.blackbooks.services.BookService;
import com.pcedu.blackbooks.services.UserDetailsImpl;
import com.pcedu.blackbooks.services.interfaces.IReviewService;
import com.pcedu.blackbooks.services.interfaces.IUserService;
import java.math.BigDecimal;
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
@RequestMapping(value = {"/api/review"})
public class ReviewRestController {

    @Autowired
    IReviewService reviewService;
    
    @Autowired
    IUserService userService;
    
    @Autowired
    BookService bookService;
    
    @CrossOrigin
    @GetMapping("/id/{id}")
    public ReviewDTO showReview(@PathVariable int id) {
        return(reviewService.findByIdDTO(id));
    }
    
    @CrossOrigin
    @GetMapping("/ids/{ids}")
    public List<ReviewDTO> showReview(@PathVariable List<Integer> ids) {
        List<ReviewDTO> reviews = new ArrayList<>();
        for (Integer id : ids) reviews.add(reviewService.findByIdDTO(id));
        return (reviews);
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<ReviewDTO> showReviews() {
        return (reviewService.findAll());
    }
    
    @CrossOrigin
    @GetMapping("/all/rating/{rating}")
    public List<ReviewDTO> showReviewsByRating(@PathVariable BigDecimal rating){
        return(reviewService.findAllByRating(rating));
    }
    
    @CrossOrigin
    @GetMapping("/all/date/{date}")
    public List<ReviewDTO> showReviewsByDate(@PathVariable Date date){
        return(reviewService.findAllByDate(date));
    }
    
    @CrossOrigin
    @GetMapping("/all/book/{bookId}")
    public List<ReviewDTO> showReviewsByBook(@PathVariable int bookId){
        return(reviewService.findAllByBook(bookId));
    }
    
    //must show and bookDTO when calling the controller
    @CrossOrigin
    @GetMapping("/all/user/{userId}")
    public List<ReviewDTO> showReviewsByUser(@PathVariable int userId){
        return(reviewService.findAllByUser(userId));
    }
    
    @Transactional 
    @CrossOrigin
    @PostMapping("/new/{bookId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity createNewReview(@RequestBody Review review, @PathVariable int bookId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        review.setUserId(userService.findByUserName(username));
        review.setBookId(bookService.findById(bookId));
        review.setReviewDatetime(new Date());
        reviewService.save(review);
        return (new ResponseEntity(new ReviewDTO(review), HttpStatus.OK));
    }
    
    @Transactional 
    @CrossOrigin
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity updateReview(@PathVariable int id, @RequestBody Review review){
        Review tempReview = updateCheck(review, reviewService.findById(id));
        if(reviewService.update(tempReview)) return (new ResponseEntity(HttpStatus.ACCEPTED));
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity deleteReview(@PathVariable int id){
        Review tempReview = reviewService.findById(id);
        if(tempReview != null){
            reviewService.delete(tempReview);
            return (new ResponseEntity(HttpStatus.ACCEPTED));
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    private Review updateCheck(Review review, Review tempReview){
        if(review.getRating() != null) tempReview.setRating(review.getRating());
        if(review.getComment()!= null) tempReview.setComment(review.getComment());
        return(tempReview);
    }
    
}
