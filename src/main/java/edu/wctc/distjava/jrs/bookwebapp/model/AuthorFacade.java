/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jrs.bookwebapp.model;

import edu.wctc.distjava.jrs.bookwebapp.repository.AuthorRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jeremy Santorelli
 */
@Service
public class AuthorFacade {

    @Autowired
    private AuthorRepository aRepo;

    public AuthorFacade() {
    }

    public List<Author> findAll() {

        return aRepo.findAll();
    }

    public Author findById(String id) {

        return aRepo.findOne(Integer.parseInt(id));
    }

    public void remove(Author author) {

        aRepo.delete(author);

    }

    public void edit(Author author) {

        aRepo.save(author);

    }

    public void createAuthor(String value) {

        Date date = new Date();
        Author newAuthor = new Author();
        newAuthor.setAuthorName(value);
        newAuthor.setDateAdded(date);
        aRepo.save(newAuthor);
    }

}
