package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.Author;
import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.dto.AuthorDTO;
import com.pcedu.blackbooks.services.interfaces.IAuthorService;
import com.pcedu.blackbooks.services.interfaces.IBookService;
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
@RequestMapping(value = {"/api/author"})
public class AuthorRestController {

    @Autowired 
    IAuthorService authorService;
    
    @Autowired 
    IBookService bookService;
    
    @CrossOrigin
    @GetMapping("/id/{id}")
    public AuthorDTO showAuthor(@PathVariable int id){
        return(authorService.findByIdDTO(id));
    }
    
    @CrossOrigin
    @GetMapping("/ids/{ids}")
    public List<AuthorDTO> showListAuthor(@PathVariable List<Integer> ids){
        List<AuthorDTO> authors = new ArrayList<>();
        for (Integer id : ids) authors.add(authorService.findByIdDTO(id));
        return(authors);
    }

    @CrossOrigin
    @GetMapping("/names/{firstName}/{lastName}")
    public List<AuthorDTO> showAuthorByNames(@PathVariable String firstName, @PathVariable String lastName){
        return(authorService.findByFirstLastNames(firstName, lastName));
    }
    
    @CrossOrigin
    @GetMapping("/all")
    public List<AuthorDTO> showAuthors(){
        return(authorService.findAll());
    }
    
    @CrossOrigin
    @GetMapping("/book/{bookId}")
    public AuthorDTO showAuthorByBook(@PathVariable int bookId){
        Book book = bookService.findById(bookId);
        return(authorService.findByBook(book));
    }
    
    @CrossOrigin
    @PostMapping("/new")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity createNewAuthor(@RequestBody Author author){
        authorService.save(author);
        return (new ResponseEntity(new AuthorDTO(author), HttpStatus.CREATED));
    }
    
    @Transactional
    @CrossOrigin
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity updateAuthor(@PathVariable int id, @RequestBody Author author){
        if(authorService.update(updateCheck(author, authorService.findById(id)))) return (new ResponseEntity(HttpStatus.ACCEPTED));
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity deleteAuthor(@PathVariable int id){
        Author tempAuthor = authorService.findById(id);
        if(tempAuthor != null){
            authorService.delete(tempAuthor);
            return (new ResponseEntity(HttpStatus.ACCEPTED));
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    private Author updateCheck(Author author, Author tempAuthor){
        if(author.getFirstName() != null) tempAuthor.setFirstName(author.getFirstName());
        if(author.getLastName() != null) tempAuthor.setLastName(author.getLastName());
        if(author.getBiography() != null) tempAuthor.setBiography(author.getBiography());
        return(tempAuthor);
    }
}
