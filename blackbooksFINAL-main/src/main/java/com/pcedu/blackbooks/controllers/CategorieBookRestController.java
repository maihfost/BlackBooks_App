package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.CategorieBook;
import com.pcedu.blackbooks.services.interfaces.IBookService;
import com.pcedu.blackbooks.services.interfaces.ICategorieBookService;
import com.pcedu.blackbooks.services.interfaces.ICategorieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/categoriebook"})
public class CategorieBookRestController {

    @Autowired
    ICategorieBookService categorieBookService;
    
    @Autowired 
    ICategorieService categorieService;
    
    @Autowired
    IBookService bookService;
    
    @Transactional
    @CrossOrigin
    @PostMapping("/new/{categorieId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity createNewCategorieBook(@PathVariable int categorieId, @RequestBody Book book){
        CategorieBook categorieBook = new CategorieBook();
        categorieBook.setCategoryId(categorieService.findById(categorieId));
        categorieBook.setBookId(bookService.findById(book.getId()));
        categorieBookService.save(categorieBook);
        return (new ResponseEntity(HttpStatus.CREATED));
    }
    
    @Transactional
    @CrossOrigin
    @PostMapping("/new/categorielist/{categorieId}/{bookId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity createNewCategoriesBook(@PathVariable List<Integer> categorieIds, @PathVariable int bookId){
        for (Integer categorieId : categorieIds) {
            CategorieBook categorieBook = new CategorieBook();
            categorieBook.setCategoryId(categorieService.findById(categorieId));
            categorieBook.setBookId(bookService.findById(bookId));
            categorieBookService.save(categorieBook);
        }
        return (new ResponseEntity(HttpStatus.CREATED));
    }
    
    @Transactional
    @CrossOrigin
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity updateCategorie(@PathVariable int id, @RequestBody CategorieBook categorieBook){
        if(categorieBookService.update(updateCheck(categorieBook, categorieBookService.findById(id)))) return (new ResponseEntity(HttpStatus.ACCEPTED));
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity deleteCategorie(@PathVariable int id){
        CategorieBook categorieBook = categorieBookService.findById(id);
        if(categorieBook != null){
            categorieBookService.deleteCategorieBook(categorieBook);
            return (new ResponseEntity(HttpStatus.ACCEPTED));
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    private CategorieBook updateCheck(CategorieBook categorieBook, CategorieBook tempCategorie){
        if(categorieBook.getBookId() != null) tempCategorie.setBookId(categorieBook.getBookId());
        if(categorieBook.getCategoryId() != null) tempCategorie.setCategoryId(categorieBook.getCategoryId());
        return(tempCategorie);
    }
}
