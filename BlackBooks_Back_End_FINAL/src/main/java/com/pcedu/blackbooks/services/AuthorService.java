package com.pcedu.blackbooks.services;

import com.pcedu.blackbooks.dao.AuthorDao;
import com.pcedu.blackbooks.entities.Author;
import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.dto.AuthorDTO;
import com.pcedu.blackbooks.services.interfaces.IAuthorService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authorService")
@Transactional
public class AuthorService implements IAuthorService{

    @Autowired
    AuthorDao authordao;
    
    @Override
    public Author findById(int id) {
        return authordao.findById(id);
    }

    @Override
    public AuthorDTO findByIdDTO(int id) {
        Author author = findById(id);
        return(new AuthorDTO(author));
    }
    
    @Override
    public List<AuthorDTO> findByFirstLastNames(String firstName, String lastName) {
        List<Author> authors = authordao.findByFirstLastNames(firstName, lastName);
        List<AuthorDTO> authorsDTO = new ArrayList<AuthorDTO>();
        for (Author author : authors) {
            authorsDTO.add(new AuthorDTO(author));
        }
        return(authorsDTO);
    }
    
    @Override
    public List<AuthorDTO> findAll() {
        List<Author> authors = authordao.findAll();
        List<AuthorDTO> authorsDTO = new ArrayList<AuthorDTO>();
        for (Author author : authors) {
            authorsDTO.add(new AuthorDTO(author));
        }
        return(authorsDTO);
    }
    
    @Override
    public AuthorDTO findByBook(Book book){
        Author author = authordao.findByBook(book);
        return(new AuthorDTO(author));
    }
    
    @Override
    public boolean save(Author author){
        boolean result = false;
        result = authordao.save(author);
        return (result);
    }
    
    @Override
    public boolean update(Author author){
        boolean result = false;
        result = authordao.update(author);
        return (result);
    }
    
    @Override
    public boolean delete(Author author){
        boolean result = false;
        result = authordao.deleteAuthor(author);
        return (result);
    }
}
