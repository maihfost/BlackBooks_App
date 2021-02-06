package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDao extends AbstractDao<Integer, Book> {

    public Book findById(int id) {
        Book b = getByKey(id);
        if (b != null) {
            return b;
        }
        return null;
    }
    
    public Book findByTitle(String title){
        for (Book book : findAll()) {
            if(book.getTitle().equals(title)) return(book);
        }
        return (new Book());
    }

    public List<Book> findAll() {
         Criteria criteria = createEntityCriteria();
         return(criteria.list());
    }
    
    public List<Book> findAllByCountSalesOrderByDesc(){
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.desc("countSales"));
        return(criteria.list());
    }
    
    public List<Book> findAllByAuthor(int authorId){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("authorId").add(Restrictions.eq("id", authorId));
        return (criteria.list());
    }
    
    public List<Book> findAllByCategorie(Categorie categorieId){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("categorieBookList").add(Restrictions.eq("categoryId", categorieId));
        return (criteria.list());
    }
    
    public Book findByShoppingCartDetail(ShoppingCartDetail detail){
        for (Book book : findAll()) {
            for (ShoppingCartDetail tempDetail : book.getShoppingCartDetailList()) {
                if(tempDetail.equals(detail))return(book);
            }
        }
        return(new Book());
    }
    
    public List<Book> findAllByPriceRange(BigDecimal min, BigDecimal max){
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.between("regularPrice", min, max));
        return(criteria.list());
    }
    
    public List<Book> findAllByHotBooks(){
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.desc("countSales"));
         return(criteria.list());
    }
    
    public List<Book> findAllByPriceDesc(){
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.desc("regularPrice"));
        return(criteria.list());
    }
    
    public List<Book> findAllByPriceAsc(){
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("regularPrice"));
        return(criteria.list());
    }
    
    public List<Book> findAllByFirstLetter(String fLetter){
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.ilike("title", fLetter +"%"));
        return (criteria.list());
    }
    
    public boolean save(Book book){
        return(persist(book));
    }
    
    public boolean update(Book book) {
        if(findById(book.getId()) != null) return(save(book));
        return(false);
    }
    
    public boolean deleteBook(Book book){
        if(book != null){
            delete(book);
            return(true);        
        }
        return(false);
    }

}
