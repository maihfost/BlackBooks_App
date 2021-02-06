package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.CategorieDao;
import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import com.pcedu.blackbooks.entities.dto.CategorieDTO;
import com.pcedu.blackbooks.services.interfaces.ICategorieService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categorieService")
@Transactional
public class CategorieService implements ICategorieService{

    @Autowired
    CategorieDao categorieDao;
    
    @Override
    public Categorie findById(int id) {
        return categorieDao.findById(id);
    }
    
    @Override
    public CategorieDTO findByIdDTO(int id){
        Categorie categorie = findById(id);
        return(new CategorieDTO(categorie));
    }

    @Override
    public CategorieDTO findByTitle(String name){
        Categorie categorie = categorieDao.findByTitle(name);
        return(new CategorieDTO(categorie));
    }
    
    @Override
    public List<CategorieDTO> findAll(){
        List<Categorie> categories = categorieDao.findAll();
        List<CategorieDTO> categoriesDTO = new ArrayList<CategorieDTO>();
        for (Categorie categorie : categories) {
            categoriesDTO.add(new CategorieDTO(categorie));
        }
        return(categoriesDTO);
    }
    
    @Override
    public List<CategorieDTO> findAllByBook(Book book){
        List<Categorie> categories = categorieDao.findAllByBook(book);
        List<CategorieDTO> categoriesDTO = new ArrayList<CategorieDTO>();
        for (Categorie categorie : categories) {
            categoriesDTO.add(new CategorieDTO(categorie));
        }
        return(categoriesDTO);
    }
    
    @Override
    public boolean save(Categorie categorie){
        boolean result = false;
        result = categorieDao.save(categorie);
        return (result);
    }
    
    @Override
    public boolean update(Categorie categorie){
        boolean result = false;
        result = categorieDao.update(categorie);
        return (result);
    }
    
    @Override
    public boolean delete(Categorie categorie){
        boolean result = false;
        result = categorieDao.deleteCategorie(categorie);
        return (result);
    }
}
