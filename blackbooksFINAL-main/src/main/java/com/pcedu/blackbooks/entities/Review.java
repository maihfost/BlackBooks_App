package com.pcedu.blackbooks.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "reviews", catalog = "blackbooks", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "book_id"})})
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findById", query = "SELECT r FROM Review r WHERE r.id = :id"),
    @NamedQuery(name = "Review.findByRating", query = "SELECT r FROM Review r WHERE r.rating = :rating"),
    @NamedQuery(name = "Review.findByReviewDatetime", query = "SELECT r FROM Review r WHERE r.reviewDatetime = :reviewDatetime"),
    @NamedQuery(name = "Review.findByComment", query = "SELECT r FROM Review r WHERE r.comment = :comment")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 2, scale = 1)
    private BigDecimal rating;
    @Basic(optional = false)
    @NotNull
    @Column(name = "review_datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDatetime;
    @Size(max = 400)
    @Column(length = 400)
    private String comment;
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false) //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Book bookId;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false) //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private User userId;

    public Review() {
    }

    public Review(Integer id) {
        this.id = id;
    }

    public Review(Integer id, Date reviewDatetime) {
        this.id = id;
        this.reviewDatetime = reviewDatetime;
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

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pcedu.blackbooks.entities.Review[ id=" + id + " ]";
    }



}
