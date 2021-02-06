package com.pcedu.blackbooks.entities.dto;

import com.pcedu.blackbooks.entities.Book;
import java.math.BigDecimal;

public class BookDTO {

    private Integer id;
    private String title;
    private String isbn;
    private String noofpages;
    private String description;
    private BigDecimal regularPrice;
    private Integer storeQuantity;
    private String image2;
    private BigDecimal totalRating;
    private Integer countReviews;
    private Integer countSales;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.noofpages = book.getNoofpages();
        this.description = book.getDescription();
        this.regularPrice = book.getRegularPrice();
        this.storeQuantity = book.getStoreQuantity();
        this.image2 = book.getImage2();
        this.totalRating = book.getTotalRating();
        this.countReviews = book.getCountReviews();
        this.countSales = book.getCountSales();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BookDTO{id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", isbn=").append(isbn);
        sb.append(", noofpages=").append(noofpages);
        sb.append(", description=").append(description);
        sb.append(", regularPrice=").append(regularPrice);
        sb.append(", storeQuantity=").append(storeQuantity);
        sb.append(", image2=").append(image2);
        sb.append(", totalRating=").append(totalRating);
        sb.append(", countReviews=").append(countReviews);
        sb.append(", countSales=").append(countSales);
        sb.append('}');
        return sb.toString();
    }

}
