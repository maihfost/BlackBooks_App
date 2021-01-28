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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "users", catalog = "blackbooks", schema = "")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByDateofbirth", query = "SELECT u FROM User u WHERE u.dateofbirth = :dateofbirth"),
    @NamedQuery(name = "User.findByCountry", query = "SELECT u FROM User u WHERE u.country = :country"),
    @NamedQuery(name = "User.findByTelephone", query = "SELECT u FROM User u WHERE u.telephone = :telephone"),
    @NamedQuery(name = "User.findByTotalPaymentAmount", query = "SELECT u FROM User u WHERE u.totalPaymentAmount = :totalPaymentAmount"),
    @NamedQuery(name = "User.findByDigitWallet", query = "SELECT u FROM User u WHERE u.digitWallet = :digitWallet")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Boolean active;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100, unique= true)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "user_name", nullable = false, length = 50, unique= true)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 60)
    @Column(nullable = false, length = 60)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;
    @Basic(optional = false)
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @Size(max = 100)
    @Column(length = 100)
    private String country;
    @Basic(optional = false)
    @Size(max = 15)
    @Column(nullable = true, length = 15)
    private String telephone;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_payment_amount", precision = 10, scale = 2)
    private BigDecimal totalPaymentAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "digit_wallet", nullable = false, precision = 10, scale = 2)
    private BigDecimal digitWallet;
    @OneToMany(mappedBy = "userId") //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ShoppingCart> shoppingCartList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId") //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Review> reviewList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId") //, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserRole> userRoleList;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String email, String userName, String password, String firstName, String lastName, Date dateofbirth, String telephone, BigDecimal digitWallet) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateofbirth = dateofbirth;
        this.telephone = telephone;
        this.digitWallet = digitWallet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public BigDecimal getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    public BigDecimal getDigitWallet() {
        return digitWallet;
    }

    public void setDigitWallet(BigDecimal digitWallet) {
        this.digitWallet = digitWallet;
    }

    @XmlTransient
    public List<ShoppingCart> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }

    @XmlTransient
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @XmlTransient
    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pcedu.blackbooks.entities.User[ id=" + id + " ]";
    }



}
