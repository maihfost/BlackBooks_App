package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.CategorieBookDao;
import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import com.pcedu.blackbooks.entities.CategorieBook;
import com.pcedu.blackbooks.services.interfaces.ICategorieBookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categorieBookService")
@Transactional
public class CategorieBookService implements ICategorieBookService{

    @Autowired
    CategorieBookDao categorieBookDao;
    
    @Override
    public CategorieBook findById(int id) {
        return(categorieBookDao.findById(id));
    }
    
    public List<CategorieBook> findByBook(Book book){
        return(categorieBookDao.findByBook(book));
    }
    
    public List<CategorieBook> findByCategorie(Categorie categorie){
        return(categorieBookDao.findByCategorie(categorie));
    }
    
    public boolean save(CategorieBook categorieBook){
        boolean result = false;
        result = categorieBookDao.save(categorieBook);
        return (result);
    }
    
    public boolean update(CategorieBook categorieBook) {
        boolean result = false;
        result = categorieBookDao.update(categorieBook);
        return (result);
    }
    
    public boolean deleteCategorieBook(CategorieBook categorieBook){
        boolean result = false;
        result = categorieBookDao.update(categorieBook);
        return (result);
    }
}
