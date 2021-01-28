package com.pcedu.blackbooks.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "order_books", catalog = "blackbooks", schema = "")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppingCartDetail.findAll", query = "SELECT s FROM ShoppingCartDetail s"),
    @NamedQuery(name = "ShoppingCartDetail.findById", query = "SELECT s FROM ShoppingCartDetail s WHERE s.id = :id"),
    @NamedQuery(name = "ShoppingCartDetail.findByPrice", query = "SELECT s FROM ShoppingCartDetail s WHERE s.price = :price"),
    @NamedQuery(name = "ShoppingCartDetail.findByQuantity", query = "SELECT s FROM ShoppingCartDetail s WHERE s.quantity = :quantity")})
public class ShoppingCartDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int quantity;
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne //(fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Book bookId;
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false) //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private ShoppingCart shoppingCartId;

    public ShoppingCartDetail() {
    }

    public ShoppingCartDetail(Integer id) {
        this.id = id;
    }

    public ShoppingCartDetail(Integer id, BigDecimal price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public ShoppingCart getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(ShoppingCart shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
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
        if (!(object instanceof ShoppingCartDetail)) {
            return false;
        }
        ShoppingCartDetail other = (ShoppingCartDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pcedu.blackbooks.entities.ShoppingCartDetail[ id=" + id + " ]";
    }



}
