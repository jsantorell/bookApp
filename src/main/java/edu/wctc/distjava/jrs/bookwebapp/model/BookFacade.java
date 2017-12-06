/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jrs.bookwebapp.model;

import edu.wctc.distjava.jrs.bookwebapp.repository.AuthorRepository;
import edu.wctc.distjava.jrs.bookwebapp.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jeremy Santorelli
 */
@Service
public class BookFacade {

    @Autowired
    BookRepository bRepo;
    @Autowired
    AuthorRepository aRepo;

    public BookFacade() {
    }

    public Set<Book1> findAllByAuthorId(int id) {

        Author author = aRepo.findOne(id);
        return author.getBookSet();
    }

    public Author getAuthorFromBook(int id) {

        return aRepo.findOne(id);

    }

    public List<Book1> findAll() {

        return bRepo.findAll();
    }

    public Book1 find(String id) {

        return bRepo.findOne(Integer.parseInt(id));
    }

    public void remove(Book1 book) {

        bRepo.delete(book);
    }

    public void create(Book1 book) {

        bRepo.save(book);
    }

    public void edit(Book1 book) {

        bRepo.save(book);
    }

}
