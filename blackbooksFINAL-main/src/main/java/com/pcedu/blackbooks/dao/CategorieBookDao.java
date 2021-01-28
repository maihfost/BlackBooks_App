package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import com.pcedu.blackbooks.entities.CategorieBook;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository("categorieBookDao")
public class CategorieBookDao extends AbstractDao<Integer, CategorieBook>{


    public CategorieBook findById(int id) {
        CategorieBook c = getByKey(id);
        if (c != null) {
            return c;
        }
        return null;
    }
    
    public List<CategorieBook> findAll() {
        Criteria criteria = createEntityCriteria();
        return(criteria.list());
    }
    
    public List<CategorieBook> findByBook(Book book){
        List<CategorieBook> categorieBooks = new ArrayList<>();
        for (CategorieBook categorieBook : findAll()) {
            if(categorieBook.getBookId().equals(book)){
                categorieBooks.add(categorieBook);
            }
        }
        return (categorieBooks);
    }
    
    public List<CategorieBook> findByCategorie(Categorie categorie){
        List<CategorieBook> categorieBooks = new ArrayList<>();
        for (CategorieBook categorieBook : findAll()) {
            if(categorieBook.getBookId().equals(categorie)){
                categorieBooks.add(categorieBook);
            }
        }
        return (categorieBooks);
    }
    
    public boolean save(CategorieBook categorieBook){
        return(persist(categorieBook));
    }
    
    public boolean update(CategorieBook categorieBook) {
        if(findById(categorieBook.getId()) != null) return(save(categorieBook));
        return(false);
    }
    
    public boolean deleteCategorie(CategorieBook categorieBook){
        if(categorieBook != null){
            delete(categorieBook);
            return(true);        
        }
        return(false);
    }

}
