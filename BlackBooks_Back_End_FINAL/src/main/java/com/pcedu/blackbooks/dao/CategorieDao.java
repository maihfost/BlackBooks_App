package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("categorieDao")
public class CategorieDao extends AbstractDao<Integer, Categorie>{

    public Categorie findById(int id) {
        Categorie c = getByKey(id);
        if (c != null) {
            return c;
        }
        return null;
    }

    public Categorie findByTitle(String name){
        for (Categorie categorie : findAll()) {
            if(categorie.getName().equals(name))return(categorie);
        }
        return (new Categorie());
    }
    
    public List<Categorie> findAllByBook(Book book){
        Criteria criteria = createEntityCriteria();
        criteria.createCriteria("categorieBookList").add(Restrictions.eq("bookId", book));
        return(criteria.list());
    }
     
    public List<Categorie> findAll() {
        Criteria criteria = createEntityCriteria();
        return(criteria.list());
    }
    
    public boolean save(Categorie categorie){
        return(persist(categorie));
    }
    
    public boolean update(Categorie categorie) {
        if(findById(categorie.getId()) != null) return(save(categorie));
        return(false);
    }
    
    public boolean deleteCategorie(Categorie categorie){
        if(categorie != null){
            delete(categorie);
            return(true);        
        }
        return(false);
    }
}
