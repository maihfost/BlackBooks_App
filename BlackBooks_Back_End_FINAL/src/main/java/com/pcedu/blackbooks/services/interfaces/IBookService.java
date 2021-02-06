package com.pcedu.blackbooks.services.interfaces;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import com.pcedu.blackbooks.entities.ShoppingCartDetail;
import com.pcedu.blackbooks.entities.dto.BookDTO;
import java.math.BigDecimal;
import java.util.List;

public interface IBookService {

    public Book findById(int id) ;
    
    public BookDTO findByIdDTO(int id);
    
    public BookDTO findByTitle(String title);

    public List<BookDTO> findAll();
    
    public List<Book> findAllBook();
    
    public List<BookDTO> findAllByHotBooks();
    
//    public List<BookDTO> findAllByPriceDesc();
//    
//    public List<BookDTO> findAllByPriceAsc();
    
    public List<BookDTO> findAllByCountSalesOrderByDesc();
    
    public List<BookDTO> findAllByAuthor(int authorId);
    
    public List<BookDTO> findAllByCategorie(Categorie categorieId);
    
    public BookDTO findByShoppingCartDetail(ShoppingCartDetail detail);
      
    public List<BookDTO> findAllByPriceRange(BigDecimal min, BigDecimal max);
    
    public List<BookDTO> findAllByFirstLetter(String fLetter);
    
    public boolean save(Book book);
    
    public boolean update(Book book);
    
    public boolean delete(Book book); 
}
