package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import com.pcedu.blackbooks.entities.CategorieBook;
import java.util.List;

public interface ICategorieBookService {
    
    public CategorieBook findById(int id);

    public List<CategorieBook> findByBook(Book book);
    
    public List<CategorieBook> findByCategorie(Categorie categorie);
    
    public boolean save(CategorieBook categorieBook);
    
    public boolean update(CategorieBook categorieBook);
    
    public boolean deleteCategorieBook(CategorieBook categorieBook);

}
