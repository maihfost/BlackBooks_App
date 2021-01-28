package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Review;
import com.pcedu.blackbooks.entities.dto.ReviewDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IReviewService {

    public Review findById(int id);

    public ReviewDTO findByIdDTO(int id);

    public List<ReviewDTO> findAll();
    
    public List<ReviewDTO> findAllByRating(BigDecimal rating);
    
    public List<ReviewDTO> findAllByDate(Date reviewDatetime);
    
    public List<ReviewDTO> findAllByBook(int bookId);
    
    public List<ReviewDTO> findAllByUser(int userId);
    
    public boolean save(Review review);
    
    public boolean update(Review review);
    
    public boolean delete(Review review);
}
