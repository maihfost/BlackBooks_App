package com.pcedu.blackbooks.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "books", catalog = "blackbooks", schema = "")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id"),
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    @NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Book.findByNoofpages", query = "SELECT b FROM Book b WHERE b.noofpages = :noofpages"),
    @NamedQuery(name = "Book.findByRegularPrice", query = "SELECT b FROM Book b WHERE b.regularPrice = :regularPrice"),
    @NamedQuery(name = "Book.findByStoreQuantity", query = "SELECT b FROM Book b WHERE b.storeQuantity = :storeQuantity"),
    @NamedQuery(name = "Book.findByTotalRating", query = "SELECT b FROM Book b WHERE b.totalRating = :totalRating"),
    @NamedQuery(name = "Book.findByCountReviews", query = "SELECT b FROM Book b WHERE b.countReviews = :countReviews"),
    @NamedQuery(name = "Book.findByCountSales", query = "SELECT b FROM Book b WHERE b.countSales = :countSales")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(nullable = false, length = 13)
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "No_of_pages", nullable = false, length = 6)
    private String noofpages;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "regular_price", nullable = false, precision = 5, scale = 2)
    private BigDecimal regularPrice;
    @Column(name = "store_quantity")
    private Integer storeQuantity;
    @Size(min = 1, max = 200)
    @Column(name="image2" , length = 200)
    private String image2;
    @Column(name = "total_rating", precision = 2, scale = 1)
    private BigDecimal totalRating;
    @Column(name = "count_reviews")
    private Integer countReviews;
    @Column(name = "count_sales")
    private Integer countSales;
    @OneToMany(mappedBy = "bookId") //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ShoppingCartDetail> shoppingCartDetailList;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne //(fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Author authorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId") //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Review> reviewList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId") //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CategorieBook> categorieBookList;

    public Book() {
    }

    public Book(Integer id) {
        this.id = id;
    }

    public Book(Integer id, String title, String isbn, String noofpages, BigDecimal regularPrice) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.noofpages = noofpages;
        this.regularPrice = regularPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNoofpages() {
        return noofpages;
    }

    public void setNoofpages(String noofpages) {
        this.noofpages = noofpages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Integer getStoreQuantity() {
        return storeQuantity;
    }

    public void setStoreQuantity(Integer storeQuantity) {
        this.storeQuantity = storeQuantity;
    }

    public String getImage2() {
        return image2;
    }
    
    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public BigDecimal getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(BigDecimal totalRating) {
        this.totalRating = totalRating;
    }

    public Integer getCountReviews() {
        return countReviews;
    }

    public void setCountReviews(Integer countReviews) {
        this.countReviews = countReviews;
    }

    public Integer getCountSales() {
        return countSales;
    }

    public void setCountSales(Integer countSales) {
        this.countSales = countSales;
    }

    @XmlTransient
    public List<ShoppingCartDetail> getShoppingCartDetailList() {
        return shoppingCartDetailList;
    }

    public void setShoppingCartDetailList(List<ShoppingCartDetail> shoppingCartDetailList) {
        this.shoppingCartDetailList = shoppingCartDetailList;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    @XmlTransient
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @XmlTransient
    public List<CategorieBook> getCategorieBookList() {
        return categorieBookList;
    }

    public void setCategorieBookList(List<CategorieBook> categorieBookList) {
        this.categorieBookList = categorieBookList;
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
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pcedu.blackbooks.entities.Book[ id=" + id + " ]";
    }



}
