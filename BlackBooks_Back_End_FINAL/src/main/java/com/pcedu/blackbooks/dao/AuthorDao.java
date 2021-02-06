package com.pcedu.blackbooks.dao;

import com.pcedu.blackbooks.entities.Author;
import com.pcedu.blackbooks.entities.Book;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("authorDao")
public class AuthorDao extends AbstractDao<Integer, Author>{

    public Author findById(int id) {
        Author a = getByKey(id);
        if (a != null) {
            return a;
        }
        return null;
    }
    
    public List<Author> findByFirstLastNames(String firstName, String lastName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("firstName", firstName));
        criteria.add(Restrictions.eq("lastName", lastName));
        return(criteria.list());
    }
    
    public List<Author> findAll() {
        Criteria criteria = createEntityCriteria();
        return(criteria.list());
    }
    
    public Author findByBook(Book book){
        for(Author author : findAll()){
            for(Book tempBook : author.getBookList()){
                if(tempBook.equals(book))return(author);
            }
        }
        return(new Author());
    }
    
    public boolean save(Author author){
        return(persist(author));
    }
    
    public boolean update(Author author) {
        if(findById(author.getId()) != null) return(save(author));
        return(false);
    }
    
    public boolean deleteAuthor(Author author){
        if(author != null){
            delete(author);
            return(true);        
        }
        return(false);
    }
}
