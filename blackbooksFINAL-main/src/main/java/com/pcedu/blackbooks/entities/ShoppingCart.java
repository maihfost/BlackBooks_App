package com.pcedu.blackbooks.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "shopping_cart", catalog = "blackbooks", schema = "")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppingCart.findAll", query = "SELECT s FROM ShoppingCart s"),
    @NamedQuery(name = "ShoppingCart.findById", query = "SELECT s FROM ShoppingCart s WHERE s.id = :id"),
    @NamedQuery(name = "ShoppingCart.findByDateTime", query = "SELECT s FROM ShoppingCart s WHERE s.dateTime = :dateTime"),
    @NamedQuery(name = "ShoppingCart.findByTotalPrice", query = "SELECT s FROM ShoppingCart s WHERE s.totalPrice = :totalPrice"),
    @NamedQuery(name = "ShoppingCart.findByIsPaid", query = "SELECT s FROM ShoppingCart s WHERE s.isPaid = :isPaid")})
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;
    private Boolean isPaid;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne //(fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCartId") //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ShoppingCartDetail> shoppingCartDetailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCartId") //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Payment> paymentList;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer id) {
        this.id = id;
    }

    public ShoppingCart(Integer id, Date dateTime) {
        this.id = id;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<ShoppingCartDetail> getShoppingCartDetailList() {
        return shoppingCartDetailList;
    }

    public void setShoppingCartDetailList(List<ShoppingCartDetail> shoppingCartDetailList) {
        this.shoppingCartDetailList = shoppingCartDetailList;
    }

    @XmlTransient
    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
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
        if (!(object instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart other = (ShoppingCart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pcedu.blackbooks.entities.ShoppingCart[ id=" + id + " ]";
    }



}
