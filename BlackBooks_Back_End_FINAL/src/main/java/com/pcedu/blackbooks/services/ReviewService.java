package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.ReviewDao;
import com.pcedu.blackbooks.entities.Review;
import com.pcedu.blackbooks.entities.dto.ReviewDTO;
import com.pcedu.blackbooks.services.interfaces.IReviewService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("reviewService")
@Transactional
public class ReviewService implements IReviewService{

    @Autowired
    ReviewDao reviewDao;

    @Override
    public Review findById(int id){
        return(reviewDao.findById(id));
    }

    @Override
    public ReviewDTO findByIdDTO(int id){
        Review review = findById(id);
        return(new ReviewDTO(review));
    }

    @Override
    public List<ReviewDTO> findAll(){
        List<Review> reviews = reviewDao.findAll();
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(new ReviewDTO(review));
        }
        return(reviewDTOs);
    }
    
    @Override
    public List<ReviewDTO> findAllByRating(BigDecimal rating){
        List<Review> reviews = reviewDao.findAllByRating(rating);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(new ReviewDTO(review));
        }
        return(reviewDTOs);
    }
    
    @Override
    public List<ReviewDTO> findAllByDate(Date reviewDatetime){
        List<Review> reviews = reviewDao.findAllByDate(reviewDatetime);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(new ReviewDTO(review));
        }
        return(reviewDTOs);
    }
    
    @Override
    public List<ReviewDTO> findAllByBook(int bookId){
        List<Review> reviews = reviewDao.findAllByBook(bookId);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(new ReviewDTO(review));
        }
        return(reviewDTOs);
    }
    
    @Override
    public List<ReviewDTO> findAllByUser(int userId){
        List<Review> reviews = reviewDao.findAllByUser(userId);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(new ReviewDTO(review));
        }
        return(reviewDTOs);
    }
    
    @Override
    public boolean save(Review review) {
        boolean result = false;
        result = reviewDao.save(review);
        return (result);
    }
    
    @Override
    public boolean update(Review review) {
        boolean result = false;
        result = reviewDao.update(review);
        return (result);
    }
    
    @Override
    public boolean delete(Review review){
        boolean result = false;
        result = reviewDao.deleteReview(review);
        return (result);
    }
    
    
    
    
}
