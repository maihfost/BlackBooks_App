package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Author;
import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.dto.AuthorDTO;
import java.util.List;

public interface IAuthorService {

    public Author findById(int id);

    public AuthorDTO findByIdDTO(int id);
    
    public List<AuthorDTO> findByFirstLastNames(String firstName, String lastName);
    
    public List<AuthorDTO> findAll();
    
    public AuthorDTO findByBook(Book book);
    
    public boolean save(Author author);
    
    public boolean update(Author author);
    
    public boolean delete(Author author);
}
