package com.pcedu.blackbooks.controllers;

import com.pcedu.blackbooks.entities.Book;
import com.pcedu.blackbooks.entities.Categorie;
import com.pcedu.blackbooks.entities.dto.BookDTO;
import com.pcedu.blackbooks.services.interfaces.IAuthorService;
import com.pcedu.blackbooks.services.interfaces.IBookService;
import com.pcedu.blackbooks.services.interfaces.ICategorieService;
import com.pcedu.blackbooks.services.interfaces.IShoppingCartDetailService;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/api/book"})
public class BookRestController {

    @Autowired
    IBookService bookService;

    @Autowired
    ICategorieService categorieService;
    
    @Autowired
    IAuthorService authorService;
    
    @Autowired
    IShoppingCartDetailService detailService;

    @CrossOrigin
    @GetMapping("/id/{id}")
    public BookDTO showBook(@PathVariable int id) {
        return(bookService.findByIdDTO(id));
    }
    
    @CrossOrigin
    @GetMapping("/ids/{ids}")
    public List<BookDTO> showListBook(@PathVariable List<Integer> ids) {
        List<BookDTO> books = new ArrayList<>();
        for (Integer id : ids) books.add(bookService.findByIdDTO(id));
        return (books);
    }

    @CrossOrigin
    @GetMapping("/title/{title}")
    public BookDTO showBookByTitle(@PathVariable String title) {
        return (bookService.findByTitle(title));
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<BookDTO> showBooks() {
        return (bookService.findAll());
    }
    
    @CrossOrigin
    @GetMapping("/all/hot")
    public List<BookDTO> showHotBooks() {
        return (bookService.findAllByHotBooks());
    }
    
//    @CrossOrigin
//    @GetMapping("/all/priceDesc")
//    public List<BookDTO> showPriceDescBooks() {
//        return (bookService.findAll());
//    }
//    
//    @CrossOrigin
//    @GetMapping("/all/priceAsc")
//    public List<BookDTO> showPriceAscBooks() {
//        return (bookService.findAll());
//    }
    
    @CrossOrigin
    @GetMapping("/all/totalsalesdesc")
    public List<BookDTO> showBooksByCountSalesOrderByDesc (){
        return(bookService.findAllByCountSalesOrderByDesc());
    }

    @CrossOrigin
    @GetMapping("/all/author/{authorId}")
    public List<BookDTO> showBooksByAuthorId(@PathVariable int authorId) {
        return (bookService.findAllByAuthor(authorId));
    }

    @CrossOrigin
    @GetMapping("/all/categories/{categorieIds}")
    public List<BookDTO> showBooksByCategorieId(@PathVariable List<Integer> categorieIds) {
        List<BookDTO> books = new ArrayList<>();
        for (Integer categorieId : categorieIds) {
            Categorie categorie = categorieService.findById(categorieId);
            for (BookDTO book : bookService.findAllByCategorie(categorie)) books.add(book);
        }
        return (books);
    }
    
    @CrossOrigin
    @GetMapping("/cartdetail/{cartDetailId}")
    @PreAuthorize("hasAuthority('user')")
    public BookDTO showBookByShoppingCartDetail(@PathVariable int cartDetailId){
        return(bookService.findByShoppingCartDetail(detailService.findById(cartDetailId)));
    }

    @CrossOrigin
    @GetMapping("/all/range/{min}/{max}")
    public List<BookDTO> showBooksByPriceRange(@PathVariable BigDecimal min, @PathVariable BigDecimal max) {
        return (bookService.findAllByPriceRange(min, max));
    }

    @CrossOrigin
    @GetMapping("/all/firstletter/{firstLetter}")
    public List<BookDTO> showBooksByFirstLetter(@PathVariable String firstLetter) {
        return (bookService.findAllByFirstLetter(firstLetter));
    }

    @CrossOrigin
    @PostMapping("/new/{authorId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity createNewBook(@RequestBody Book book, @PathVariable int authorId){ 
        if(authorId != 0) book.setAuthorId(authorService.findById(authorId));
        bookService.save(book);
        return (new ResponseEntity(new BookDTO(book), HttpStatus.CREATED));
    }
    
    @CrossOrigin
    @PostMapping("/new/image/{bookId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity createNewBookImage(@RequestPart("file") MultipartFile file, @PathVariable int bookId) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(file.getOriginalFilename());
                Files.write(path, bytes);
                Book book = bookService.findById(bookId);
                book.setImage(bytes);
                if (bookService.update(book)) return (new ResponseEntity(new BookDTO(book), HttpStatus.CREATED));
                return (new ResponseEntity(HttpStatus.BAD_REQUEST));
                
            } catch (IOException e) {
		System.out.println(e.getMessage());
            }
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    @Transactional
    @CrossOrigin
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity updateBook(@PathVariable int id, @RequestBody Book book) {
        if(bookService.update(updateCheck(book, bookService.findById(id)))) return (new ResponseEntity(HttpStatus.ACCEPTED));
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity deleteBook(@PathVariable int id) {
        Book tempBook = bookService.findById(id);
        if(tempBook != null){
            bookService.delete(tempBook);
            return (new ResponseEntity(HttpStatus.ACCEPTED));
        }
        return (new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
    
    private Book updateCheck(Book book, Book tempBook){
        if(book.getTitle() != null) tempBook.setTitle(book.getTitle());
        if(book.getIsbn() != null) tempBook.setIsbn(book.getIsbn());
        if(book.getNoofpages() != null) tempBook.setNoofpages(book.getNoofpages());
        if(book.getDescription() != null) tempBook.setDescription(book.getDescription());
        if(book.getRegularPrice() != null) tempBook.setRegularPrice(book.getRegularPrice());
        if(book.getStoreQuantity() !=null) tempBook.setStoreQuantity(book.getStoreQuantity());
//        if(book.getImage() != null) tempBook.setImage(book.getImage());
        if(book.getTotalRating() != null) tempBook.setTotalRating(book.getTotalRating());
        if(book.getCountReviews() !=null) tempBook.setCountReviews(book.getCountReviews());
        if(book.getCountSales() != null) tempBook.setCountSales(book.getCountSales());
        return(tempBook);
    }
}
