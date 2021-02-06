package com.pcedu.blackbooks.entities.dto;

import com.pcedu.blackbooks.entities.Review;
import java.math.BigDecimal;
import java.util.Date;

public class ReviewDTO {

    private Integer id;
    private BigDecimal rating;
    private Date reviewDatetime;
    private String comment;

    public ReviewDTO() {
    }

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.rating = review.getRating();
        this.reviewDatetime = review.getReviewDatetime();
        this.comment = review.getComment();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Date getReviewDatetime() {
        return reviewDatetime;
    }

    public void setReviewDatetime(Date reviewDatetime) {
        this.reviewDatetime = reviewDatetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReviewDTO{id=").append(id);
        sb.append(", rating=").append(rating);
        sb.append(", reviewDatetime=").append(reviewDatetime);
        sb.append(", comment=").append(comment);
        sb.append('}');
        return sb.toString();
    }

}
