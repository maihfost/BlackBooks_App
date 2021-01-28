package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import com.pcedu.blackbooks.entities.dto.CategorieDTO;
import java.util.List;

public interface ICategorieService {

    public Categorie findById(int id);
    
    public CategorieDTO findByIdDTO(int id);

    public CategorieDTO findByTitle(String name);
    
    public List<CategorieDTO> findAll();
    
    public List<CategorieDTO> findAllByBook(Book book);
    
    public boolean save(Categorie categorie);
    
    public boolean update(Categorie categorie);
    
    public boolean delete(Categorie categorie);
}
