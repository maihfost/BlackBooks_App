package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Review;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("reviewDao")
public class ReviewDao extends AbstractDao<Integer, Review> {

    public Review findById(int id) {
        Review r = getByKey(id);
        if(r != null) {
            return r;
        }
        return null;
    }

    public List<Review> findAll() {
         Criteria criteria = createEntityCriteria();
         return(criteria.list());
    }
    
    public List<Review> findAllByRating(BigDecimal rating) {
         Criteria criteria = createEntityCriteria();
         criteria.add(Restrictions.eq("rating", rating));
         return(criteria.list());
    }
    
    public List<Review> findAllByDate(Date reviewDatetime) {
         Criteria criteria = createEntityCriteria();
         criteria.add(Restrictions.eq("reviewDatetime", reviewDatetime));
         return(criteria.list());
    }
    
    public List<Review> findAllByBook(int bookId){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("bookId").add(Restrictions.eq("id", bookId));
        return (criteria.list());
    }
    
    public List<Review> findAllByUser(int userId){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("userId").add(Restrictions.eq("id", userId));
        return (criteria.list());
    }
    
    public boolean save(Review review) {
        review.setReviewDatetime(new Date());
        return(persist(review));
    }
    
    public boolean update(Review review) {
        if(findById(review.getId()) != null) return(save(review));
        return(false);
    }
    
    public boolean deleteReview(Review review) {
        if(review != null) {
            delete(review);
            return(true);
        }
        return(false);
    }
    
}
