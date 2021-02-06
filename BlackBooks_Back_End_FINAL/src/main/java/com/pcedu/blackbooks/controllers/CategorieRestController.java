package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import com.pcedu.blackbooks.entities.dto.CategorieDTO;
import com.pcedu.blackbooks.services.interfaces.IBookService;
import com.pcedu.blackbooks.services.interfaces.ICategorieService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/categorie"})
public class CategorieRestController {

    @Autowired 
    ICategorieService categorieService;
    
    @Autowired
    IBookService bookService;
    
    @CrossOrigin
    @GetMapping("/id/{id}")
    public CategorieDTO showCategorie(@PathVariable int id){
        return(categorieService.findByIdDTO(id));
    }
    
    @CrossOrigin
    @GetMapping("/ids/{ids}")
    public List<CategorieDTO> showListCategorie(@PathVariable List<Integer> ids){
        List<CategorieDTO> categories = new ArrayList<>();
        for (Integer id : ids) categories.add(categorieService.findByIdDTO(id));
        return(categories);
    }

    @CrossOrigin
    @GetMapping("/title/{title}")
    public CategorieDTO showCategorieByName(@PathVariable String title){
        return(categorieService.findByTitle(title));
    }
    
    @CrossOrigin
    @GetMapping("/all")
    public List<CategorieDTO> showCategories(){
        return(categorieService.findAll());
    }
    
    @CrossOrigin
    @GetMapping("/book/{bookId}")
    public List<CategorieDTO> showCategoriesByBook(@PathVariable int bookId){
        Book book = bookService.findById(bookId);
        return(categorieService.findAllByBook(book));
    }
    
    @CrossOrigin
    @PostMapping("/new")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity createNewCategorie(@RequestBody Categorie categorie){
        categorieService.save(categorie);
        return (new ResponseEntity(new CategorieDTO(categorie), HttpStatus.CREATED));
    }
    
    @Transactional
    @CrossOrigin
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity updateCategorie(@PathVariable int id, @RequestBody Categorie categorie){
        if(categorieService.update(updateCheck(categorie, categorieService.findById(id)))) return (new ResponseEntity(HttpStatus.ACCEPTED));
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity deleteCategorie(@PathVariable int id){
        Categorie tempCategorie = categorieService.findById(id);
        if(tempCategorie != null){
            categorieService.delete(tempCategorie);
            return (new ResponseEntity(HttpStatus.ACCEPTED));
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    private Categorie updateCheck(Categorie categorie, Categorie tempCategorie){
        if(categorie.getName() != null) tempCategorie.setName(categorie.getName());
        return(tempCategorie);
    }
}
